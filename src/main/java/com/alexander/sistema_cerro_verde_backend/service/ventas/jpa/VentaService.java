package com.alexander.sistema_cerro_verde_backend.service.ventas.jpa;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alexander.sistema_cerro_verde_backend.dto.ventas.VentaDTO;
import com.alexander.sistema_cerro_verde_backend.entity.caja.TipoTransacciones;
import com.alexander.sistema_cerro_verde_backend.entity.caja.TransaccionesCaja;
import com.alexander.sistema_cerro_verde_backend.entity.compras.MovimientosInventario;
import com.alexander.sistema_cerro_verde_backend.entity.compras.Productos;
import com.alexander.sistema_cerro_verde_backend.entity.ventas.DetalleVenta;
import com.alexander.sistema_cerro_verde_backend.entity.ventas.VentaHabitacion;
import com.alexander.sistema_cerro_verde_backend.entity.ventas.VentaMetodoPago;
import com.alexander.sistema_cerro_verde_backend.entity.ventas.VentaSalon;
import com.alexander.sistema_cerro_verde_backend.entity.ventas.Ventas;
import com.alexander.sistema_cerro_verde_backend.repository.caja.CajasRepository;
import com.alexander.sistema_cerro_verde_backend.repository.caja.TransaccionesCajaRepository;
import com.alexander.sistema_cerro_verde_backend.repository.compras.MovimientosInventarioRepository;
import com.alexander.sistema_cerro_verde_backend.repository.compras.ProductosRepository;
import com.alexander.sistema_cerro_verde_backend.repository.recepcion.ReservasRepository;
import com.alexander.sistema_cerro_verde_backend.repository.ventas.DetalleVentaRepository;
import com.alexander.sistema_cerro_verde_backend.repository.ventas.MetodoPagoRepository;
import com.alexander.sistema_cerro_verde_backend.repository.ventas.VentaHabitacionRepository;
import com.alexander.sistema_cerro_verde_backend.repository.ventas.VentaMetodoPagoRepository;
import com.alexander.sistema_cerro_verde_backend.repository.ventas.VentaSalonRepository;
import com.alexander.sistema_cerro_verde_backend.repository.ventas.VentasRepository;
import com.alexander.sistema_cerro_verde_backend.service.ventas.IVentaService;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import jakarta.persistence.EntityNotFoundException;

@Service
public class VentaService implements IVentaService {

    @Autowired
    private VentasRepository repoVenta;

    @Autowired
    private ProductosRepository repoProductos;

    @Autowired
    private ReservasRepository repoReservas;

    @Autowired
    private MovimientosInventarioRepository repoMovimientosInventario;

    @Autowired
    private MetodoPagoRepository repoMetodo;

    @Autowired
    private CajasRepository repoCaja;

    @Autowired
    private DetalleVentaRepository repoDetalle;

    @Autowired
    private VentaHabitacionRepository repoVentaHabitacion;

    @Autowired
    private VentaSalonRepository repoVentaSalon;

    @Autowired
    private VentaMetodoPagoRepository repoVentaMetodoPago;

    @Autowired
    private TransaccionesCajaRepository repoTransacciones;

    @Override
    public List<Ventas> buscarTodos() {
        return repoVenta.findAll();
    }

    @Override
    public Optional<Ventas> buscarPorId(Integer id) {
        return repoVenta.findById(id);
    }

    @Override
    public VentaDTO convertirDTO(Ventas venta) {
        VentaDTO dto = new VentaDTO();
        dto.setIdVenta(venta.getIdVenta());
        dto.setNumComprobante(venta.getComprobantePago().getNumComprobante());
        dto.setNumSerieBoleta(venta.getComprobantePago().getNumSerieBoleta());
        dto.setNumSerieFactura(venta.getComprobantePago().getNumSerieFactura());
        dto.setDniRucCliente(venta.getCliente().getDniRuc());
        dto.setCliente(venta.getCliente().getNombre());
        dto.setFecha(venta.getFecha());
        dto.setTotal(venta.getTotal());
        dto.setDescuento(venta.getDescuento());
        dto.setCargo(venta.getCargo());
        dto.setIgv(venta.getIgv());
        dto.setDetalleVenta(venta.getDetalleVenta());
        dto.setDetalleHabitacion(venta.getVentaHabitacion());
        dto.setDetalleSalon(venta.getVentaSalon());
        dto.setMetodosPago(venta.getVentaMetodoPago());
        dto.setEstadoVenta(venta.getEstadoVenta());

        return dto;
    }

    @Override
    public void guardar(Ventas venta) {
        // 1. Guardar la venta
        Ventas ventaGuardada = repoVenta.save(venta);

        // 2. Ajustar stock de productos
        ventaGuardada.getDetalleVenta().forEach(det -> {
            Integer prodId = det.getProducto().getId_producto();
            var producto = repoProductos.findById(prodId)
                    .orElseThrow(() -> new EntityNotFoundException("Producto no existe: " + prodId));

            MovimientosInventario movimiento = new MovimientosInventario();
            movimiento.setProducto(producto);
            movimiento.setTipo_movimiento("Salida");
            movimiento.setFecha(venta.getFecha());
            movimiento.setVenta(ventaGuardada);

            int cantidad = (int) det.getCantidad();
            movimiento.setCantidad(cantidad);
            repoMovimientosInventario.save(movimiento);

            producto.setStock(producto.getStock() - cantidad);
            repoProductos.save(producto);
        });

        // 3. Actualizar estado de reserva
        ventaGuardada.getVentaXReserva().forEach(r -> {
            Integer idReserva = r.getReserva().getId_reserva();
            var reserva = repoReservas.findById(idReserva)
                    .orElseThrow(() -> new EntityNotFoundException("Reserva: " + idReserva));
            reserva.setEstado_reserva("Pagada");
            repoReservas.save(reserva);
        });

        // 4. Registrar transacciones en caja si hay métodos de pago
        var caja = repoCaja.findByEstadoCaja("abierta")
                .orElseThrow(() -> new RuntimeException("Caja no encontrada"));

        for (VentaMetodoPago m : ventaGuardada.getVentaMetodoPago()) {
            Integer idMetodoPago = m.getMetodoPago().getIdMetodoPago();
            var metodoPago = repoMetodo.findById(idMetodoPago)
                    .orElseThrow(() -> new EntityNotFoundException("Método de Pago: " + idMetodoPago));

            double monto = m.getPago();
            if (monto > 0) {
                // Actualizar saldo total
                caja.setSaldoTotal(caja.getSaldoTotal() + monto);

                // Actualizar saldo físico si es efectivo
                if ("Efectivo".equalsIgnoreCase(metodoPago.getNombre())) {
                    caja.setSaldoFisico(caja.getSaldoFisico() + monto);
                }

                // 🔽 Transacción para cualquier método de pago
                TransaccionesCaja transaccion = new TransaccionesCaja();
                transaccion.setMontoTransaccion(monto);
                transaccion.setFechaHoraTransaccion(new Date());
                transaccion.setCaja(caja);
                transaccion.setVenta(ventaGuardada); // ✅ Relación con venta
                transaccion.setMetodoPago(metodoPago); // ✅ Relación con método de pago

                TipoTransacciones tipoIngreso = new TipoTransacciones();
                tipoIngreso.setId(1); // 1 = ingreso
                transaccion.setTipo(tipoIngreso);

                repoTransacciones.save(transaccion);

                // Guardar caja después de registrar la transacción
                repoCaja.save(caja);
            }
        }
    }

    // ---------- SPRING BOOT ----------
    @Override
    @Transactional
    public void modificar(Ventas venta) {
        // 1. Obtener venta existente
        Ventas ventaExistente = repoVenta.findById(venta.getIdVenta())
                .orElseThrow(() -> new EntityNotFoundException("Venta no encontrada con ID: " + venta.getIdVenta()));

        // 2. Revertir stock anterior
        for (DetalleVenta detalle : ventaExistente.getDetalleVenta()) {
            Integer idProd = detalle.getProducto().getId_producto();
            Productos producto = repoProductos.findById(idProd)
                    .orElseThrow(() -> new EntityNotFoundException("Producto no existe: " + idProd));
            producto.setStock(producto.getStock() + detalle.getCantidad());
            repoProductos.save(producto);
        }

        // 3. Eliminar detalles y métodos de pago antiguos
        ventaExistente.getDetalleVenta().clear();
        ventaExistente.getVentaMetodoPago().forEach(vmp -> repoVentaMetodoPago.delete(vmp));
        ventaExistente.getVentaMetodoPago().clear();

        // 4. Agregar nuevos detalles y ajustar stock
        for (DetalleVenta nuevoDetalle : venta.getDetalleVenta()) {
            Integer idProd = nuevoDetalle.getProducto().getId_producto();
            Productos producto = repoProductos.findById(idProd)
                    .orElseThrow(() -> new EntityNotFoundException("Producto no existe: " + idProd));

            int cantidadVendida = nuevoDetalle.getCantidad();
            if (producto.getStock() < cantidadVendida) {
                throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre());
            }

            producto.setStock(producto.getStock() - cantidadVendida);
            repoProductos.save(producto);

            nuevoDetalle.setVenta(ventaExistente);
            ventaExistente.getDetalleVenta().add(nuevoDetalle);

            // Registrar movimiento inventario
            MovimientosInventario mov = new MovimientosInventario();
            mov.setProducto(producto);
            mov.setTipo_movimiento("Salida");
            mov.setCantidad(cantidadVendida);
            mov.setFecha(venta.getFecha());
            mov.setVenta(ventaExistente);
            repoMovimientosInventario.save(mov);
        }

        // 5. Agregar nuevos métodos de pago
        for (VentaMetodoPago metodo : venta.getVentaMetodoPago()) {
            VentaMetodoPago nuevoMetodo = new VentaMetodoPago();
            nuevoMetodo.setPago(metodo.getPago());
            nuevoMetodo.setMetodoPago(metodo.getMetodoPago());
            nuevoMetodo.setVenta(ventaExistente);
            ventaExistente.getVentaMetodoPago().add(nuevoMetodo);
        }

        // 6. Actualizar campos generales
        ventaExistente.setFecha(venta.getFecha());
        ventaExistente.setEstadoVenta(venta.getEstadoVenta());
        ventaExistente.setTotal(venta.getTotal());
        ventaExistente.setDescuento(venta.getDescuento());
        ventaExistente.setCargo(venta.getCargo());
        ventaExistente.setIgv(venta.getIgv());
        ventaExistente.setComprobantePago(venta.getComprobantePago());

        // 7. Guardar cambios
        repoVenta.save(ventaExistente);
    }

    @Override
    public void eliminar(Integer id) {
        repoVenta.deleteById(id);
    }

    @Override
    public String generarComprobante(Integer id) {
        String correlativo;
        String tipo;

        Ventas venta = repoVenta.findById(id).orElseThrow();

        if (venta.getComprobantePago().getNumComprobante().equals("B001")) {
            correlativo = venta.getComprobantePago().getNumSerieBoleta();
            tipo = "Boleta";
        } else {
            correlativo = venta.getComprobantePago().getNumSerieFactura();
            tipo = "Factura";
        }

        StringBuilder filas = new StringBuilder();

        // Obtener detalles
        List<DetalleVenta> productos = repoDetalle.findByVenta_IdVenta(id);
        List<VentaHabitacion> habitaciones = repoVentaHabitacion.findByVenta_IdVenta(id);
        List<VentaSalon> salones = repoVentaSalon.findByVenta_IdVenta(id);

        //Subtotal
        Double subTotal = 0.0;
        Double igv = 0.0;

        // Agregamos productos
        for (DetalleVenta d : productos) {
            subTotal = subTotal + d.getSubTotal();
            filas.append("<tr>")
                    .append("<td>").append(d.getCantidad()).append("</td>")
                    .append("<td>").append(d.getProducto().getNombre()).append("</td>")
                    .append("<td>S/ ").append(d.getProducto().getPrecioVenta()).append("</td>")
                    .append("<td>S/ ").append(d.getSubTotal()).append("</td>")
                    .append("</tr>");
        }

        // Agregamos habitaciones
        for (VentaHabitacion h : habitaciones) {
            subTotal = subTotal + h.getSubTotal();
            filas.append("<tr>")
                    .append("<td>1</td>")
                    .append("<td>").append(h.getHabitacion().getNumero())
                    .append(" Piso ").append(h.getHabitacion().getPiso())
                    .append(" ").append(h.getHabitacion().getTipo_habitacion().getNombre())
                    .append(" X ").append(h.getDias()).append(" dias").append("</td>")
                    .append("<td>S/ ").append(h.getHabitacion().getTipo_habitacion().getPrecio()).append("</td>")
                    .append("<td>S/ ").append(h.getSubTotal()).append("</td>")
                    .append("</tr>");
        }

        // Agregamos salones
        for (VentaSalon s : salones) {
            subTotal = subTotal + s.getSubTotal();
            filas.append("<tr>")
                    .append("<td>1</td>")
                    .append("<td>").append(s.getSalon().getNombre())
                    .append(" X ").append(s.getDias()).append(" dias").append("</td>")
                    .append("<td>S/ ").append(s.getSalon().getPrecio_diario()).append("</td>")
                    .append("<td>S/ ").append(s.getSubTotal()).append("</td>")
                    .append("</tr>");
        }

        igv = subTotal * (venta.getIgv() / 100);
        String html = """
                <!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8"/>
    <style>
        @page {
            size: 50mm auto;
            margin: 5mm;
        }

        body {
            font-family: Arial, sans-serif;
            font-size: 10px;
        }

        .centrado {
            text-align: center;
        }

        .linea {
            border-top: 1px dashed #000;
            margin: 4px 0;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        td {
            padding: 2px 0;
        }

        .total {
            font-weight: bold;
            font-size: 11px;
        }
    </style>
</head>

<body>
    <div class="centrado">
        <img src="file:src/main/resources/static/img/logo-cerroverde2.png" width="100" alt="Logo"/><br/>
        <strong>CERRO VERDE TARAPOTO HOTEL S.A.C.</strong><br/>
        RUC: 20531481233<br/>
        Jr. Augusto B. Leguia Nro. 596<br/>
        ---------------------------------<br/>
        <strong>"""
                + tipo
                + """
        </strong><br/>
        """
                + venta.getComprobantePago().getNumComprobante()
                + """ 
         - 
         """
                + correlativo
                + """
         <br/>
        Fecha: """
                + venta.getFecha()
                + """
    </div>

    <div>
        <p style="margin: 0;">DNI/RUC: """ + venta.getCliente().getDniRuc()
                + """
         </p>
        <p style="margin: 0;">Cliente: """ + venta.getCliente().getNombre()
                + """ 
        </p>
    </div>

    <div class="linea"></div>

    <table>
        <thead>
            <th>Cant.</th>
            <th>Descrip.</th>
            <th>Precio U.</th>
            <th>Valor.</th>
        </thead>
        <tbody>
            """
                + filas.toString()
                + """
        </tbody>

    </table>

    <div class="linea"></div>

    <table>
        <tr>
            <td>Subtotal</td>
            <td style="text-align: right;">S/. """
                + subTotal.toString()
                + """
            </td>
        </tr>
        <tr>
            <td>Descuento</td>
            <td style="text-align: right;">S/. """
                + venta.getDescuento()
                + """
            </td>
        </tr>
        <tr>
            <td>IGV (18%)</td>
            <td style="text-align: right;">S/. """
                + igv.toString()
                + """
             </td>
        </tr>
        <tr>
            <td>Cargo</td>
            <td style="text-align: right;">S/. """
                + venta.getCargo()
                + """
            </td>
        </tr>
        <tr class="total">
            <td>Total</td>
            <td style="text-align: right;">S/. """
                + venta.getTotal()
                + """
            </td>
        </tr>
    </table>

    <div class="centrado">
        <div class="linea"></div>
        ¡Gracias por su compra!
    </div>
</body>

</html>
                """;
        return html;
    }

    @Override
    public byte[] generarPdf(Integer id) {
        String html = generarComprobante(id); // Este método debe generar el HTML como string

        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(html, null);
            builder.useFastMode();
            builder.toStream(os);
            builder.run();
            return os.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error al generar PDF", e);
        }
    }
}

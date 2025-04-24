package com.alexander.sistema_cerro_verde_backend.service.jpa;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.alexander.sistema_cerro_verde_backend.entity.PasswordResetToken;
import com.alexander.sistema_cerro_verde_backend.entity.Usuarios;
import com.alexander.sistema_cerro_verde_backend.repository.PasswordResetTokenRepository;
import com.alexander.sistema_cerro_verde_backend.repository.UsuariosRepository;
import com.alexander.sistema_cerro_verde_backend.service.IPasswordResetService;

@Service
public class PasswordResetService implements IPasswordResetService {

    @Autowired
    private UsuariosRepository usuarioRepository;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private JavaMailSender mailSender;
    
    @Override
    public void enviarLinkRecuperacion(String email) {
        Optional<Usuarios> usuarioOpt = usuarioRepository.findByEmail(email);

        if (usuarioOpt.isPresent()) {
            Usuarios usuario = usuarioOpt.get();
            String token = UUID.randomUUID().toString();

            PasswordResetToken resetToken = new PasswordResetToken();
            resetToken.setToken(token);
            resetToken.setUsuario(usuario);
            resetToken.setExpiryDate(LocalDateTime.now().plusMinutes(30));

            tokenRepository.save(resetToken);

            String link = "http://localhost:4200/reset-password?token=" + token;

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(usuario.getEmail());
            message.setSubject("Recuperación de contraseña");
            message.setText("Haz clic aquí para restablecer tu contraseña: " + link);

            mailSender.send(message);
        }
    }

}

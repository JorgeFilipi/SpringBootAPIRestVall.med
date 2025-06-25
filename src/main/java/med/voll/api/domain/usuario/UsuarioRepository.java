package med.voll.api.domain.usuario;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.function.Function;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

  UserDetails findByLogin(String login);

}

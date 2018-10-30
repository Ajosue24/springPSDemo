package com.proasecal.web.repository.seguridad;


import com.proasecal.web.entity.seguridad.Usuarios;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuarios,Long> {
}

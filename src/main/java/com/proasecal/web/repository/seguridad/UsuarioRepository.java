package com.proasecal.web.repository.seguridad;


import com.proasecal.web.entity.seguridad.Usuarios;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuarios,Long> {
	Optional<Usuarios> findByNombreUsuario(String nombreUsuario);

	Boolean existsByNombreUsuario(String nombreUsuario);

	Boolean existsByCodProasecal(Integer CodigoProasecal);
}

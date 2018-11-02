package com.proasecal.web.repository.seguridad;


import com.proasecal.web.entity.seguridad.Roles;
import org.springframework.data.repository.CrudRepository;

public interface RolRepository extends CrudRepository<Roles,Long> {

    public boolean existsByNombreRol(String nombre);


}

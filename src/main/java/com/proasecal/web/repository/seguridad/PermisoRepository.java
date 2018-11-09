package com.proasecal.web.repository.seguridad;

import com.proasecal.web.entity.seguridad.Modulos;
import com.proasecal.web.entity.seguridad.Permisos;
import com.proasecal.web.entity.seguridad.Roles;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PermisoRepository extends CrudRepository<Permisos, Long> {

    List<Permisos> findAllByIdModulos(Modulos modulos);
    List<Permisos> findAllByListRoles(Roles roles);
    List<Permisos> findAll();
    Permisos findByUrl(String url);


}

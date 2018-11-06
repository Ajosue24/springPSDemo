package com.proasecal.web.repository.parametricas;


import com.proasecal.web.entity.parametricas.Pais;
import com.proasecal.web.entity.parametricas.TipoDocumentoPais;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IdTipoPaisRepository extends CrudRepository<TipoDocumentoPais,Long> {

    List<TipoDocumentoPais> findByIdPais(Pais idPais);

}

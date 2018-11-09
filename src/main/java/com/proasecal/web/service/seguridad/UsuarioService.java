package com.proasecal.web.service.seguridad;

import com.proasecal.web.entity.seguridad.Usuarios;
import com.proasecal.web.repository.seguridad.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UsuarioService {

    @Autowired
    UsuarioRepository usuariosRepository;



    public List<Usuarios> list(){
        return (List<Usuarios>)usuariosRepository.findAll();
    }

    public Usuarios get(Long id){
        return usuariosRepository.findById(id).get();
    }
    public void save(Usuarios usuarios){
        usuariosRepository.save(usuarios);
    }


    public Boolean validarUsuarioExistente(String nombreUsuario){
        return usuariosRepository.existsByNombreUsuario(nombreUsuario);
    }

    public Boolean validarCodProasecalExistente(Integer codigoProasecal){
        return usuariosRepository.existsByCodProasecal(codigoProasecal);
    }
}

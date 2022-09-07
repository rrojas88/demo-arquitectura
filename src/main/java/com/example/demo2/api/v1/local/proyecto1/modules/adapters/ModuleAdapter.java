
package com.example.demo2.api.v1.local.proyecto1.modules.adapters;

import com.example.demo2.api.v1.local.Utils.ErrorService;
import com.example.demo2.api.v1.local.proyecto1.modules.adapters.bd1.Module1;
import com.example.demo2.api.v1.local.proyecto1.modules.adapters.bd1.ModuleRepository1;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ModuleAdapter {

    @Autowired
    ModuleRepository1 moduleRepository;

    private String myClassName = ModuleAdapter.class.getName();

    public Object getAll() {
        try {
            return moduleRepository.findAll();
        } catch (Exception e) {
            return new ErrorService(
                "No se listaron los Módulos.",
                e.getMessage(),
                this.myClassName
            );
        }
    }

    public Object getById(Integer id) {
        try {
            Optional<Module1> rowOptional = moduleRepository.findById(id);
            if (!rowOptional.isPresent() || rowOptional.isEmpty()) {
                return new ErrorService(id.toString(), "", this.myClassName, 404);
            }
            return rowOptional;
        } catch (Exception e) {
            return new ErrorService(
                "No se obtuvo el Módulo",
                e.getMessage(),
                this.myClassName
            );
        }
    }

    public Object getByName(String name) {
        try {
            return moduleRepository.findByName(name);
        } catch (Exception e) {
            return new ErrorService(
                "No se obtuvo el Módulo",
                e.getMessage(),
                this.myClassName
            );
        }
    }

    public Object getByCode(String code) {
        try {
            return moduleRepository.findByCode(code);
        } catch (Exception e) {
            return new ErrorService(
                "No se obtuvo el Módulo",
                e.getMessage(),
                this.myClassName
            );
        }
    }

    public Object save(ModuleDto moduleDto) {
        try {
            Module1 mod = new Module1();
            if( moduleDto.getId() != null ){
                mod.setId( moduleDto.getId() );
                mod.setCode( moduleDto.getCode() );
                mod.setName( moduleDto.getName() );
                mod.setActive( moduleDto.getActive() );
            }
            else{
                mod.setCode( moduleDto.getCode() );
                mod.setName( moduleDto.getName() );
            }
            return moduleRepository.save(mod);
        } catch (Exception e) {
            return new ErrorService(
                "No se pudo guardar el Módulo",
                e.getMessage(),
                this.myClassName
            );
        }
    }

    public Object delete(Integer id) {
        try {
            Optional<Module1> row = moduleRepository.findById(id);
            if (!row.isEmpty()) {
                moduleRepository.deleteById(id);
                return "Se eliminó el registro con ID: " + id;
            }
            return "No se encontró el registro con ID: " + id;
        } catch (Exception e) {
            return new ErrorService(
                "No se eliminó el Módulo",
                e.getMessage(),
                this.myClassName
            );
        }
    }
}

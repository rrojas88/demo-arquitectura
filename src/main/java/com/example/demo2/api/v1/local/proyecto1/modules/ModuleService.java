
package com.example.demo2.api.v1.local.proyecto1.modules;

import com.example.demo2.api.v1.local.Utils.ErrorService;
import com.example.demo2.api.v1.local.Utils.UtilsService;
import com.example.demo2.api.v1.local.proyecto1.actions.ActionService;
import com.example.demo2.api.v1.local.proyecto1.modules.adapters.ModuleAdapter;
import com.example.demo2.api.v1.local.proyecto1.modules.adapters.ModuleDto;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ModuleService {

    @Autowired
    ModuleAdapter moduleAdapter;
    
    @Autowired
    ActionService actionService;

    private String myClassName = ModuleService.class.getName();

    public Object getAll() {
        try {
            return moduleAdapter.getAll();
        } catch (Exception e) {
            return new ErrorService(
                "No se listaron los módulos",
                e.getMessage(),
                this.myClassName
            );
        }
    }

    public Object getById(Integer id) {
        try {
            return moduleAdapter.getById(id);
        } catch (Exception e) {
            return new ErrorService(
                "No se obtuvo el módulo",
                e.getMessage(),
                this.myClassName
            );
        }
    }

    public Object getByName(String name) {
        try {
            return moduleAdapter.getByName(name);
        } catch (Exception e) {
            return new ErrorService(
                "No se obtuvo el módulo",
                e.getMessage(),
                this.myClassName
            );
        }
    }

    public Object getByCode(String code) {
        try {
            return moduleAdapter.getByCode(code);
        } catch (Exception e) {
            return new ErrorService(
                "No se obtuvo el módulo",
                e.getMessage(),
                this.myClassName
            );
        }
    }

    public Object save(ModuleDto module) {
        try {
            return moduleAdapter.save(module);
        } catch (Exception e) {
            return new ErrorService(
                "No se pudo guardar el módulo",
                e.getMessage(),
                this.myClassName
            );
        }
    }

    public Object delete(Integer id) {
        try {
            Object cities = actionService.getByModule_id(id);
            if (UtilsService.isErrorService(cities)) {
                return cities;
            }
            if (cities != null && !((ArrayList) cities).isEmpty()) {
                return new ErrorService(
                    "No se puede eliminar el módulo porque tiene acciones asociadas",
                    "",
                    this.myClassName
                );
            }
            return moduleAdapter.delete(id);
        } catch (Exception e) {
            return new ErrorService(
               "No se eliminó el módulo",
                e.getMessage(),
                this.myClassName
            );
        }
    }
}

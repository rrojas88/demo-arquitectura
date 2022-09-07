
package com.example.demo2.api.v1.local.proyecto1.actions;

import com.example.demo2.api.v1.local.Utils.ErrorService;
import com.example.demo2.api.v1.local.proyecto1.actions.adapters.ActionAdapter;
import com.example.demo2.api.v1.local.proyecto1.actions.adapters.ActionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ActionService {
    
    @Autowired
    ActionAdapter actionAdapter;
    
    private String myClassName = ActionService.class.getName();
    
    public Object getAll(){
        try {
            return actionAdapter.getAll();
        }
        catch (Exception e) {
            return new ErrorService(
                "No se listaron las acciones", 
                e.getMessage(), 
                this.myClassName
            );
        }
    }
    
    public Object getById(Integer id){
        try {
            return actionAdapter.getById(id);
        }
        catch (Exception e) {
            return new ErrorService(
                "No se obtuvo la acción", 
                e.getMessage(), 
                this.myClassName
            );
        }
    }

    public Object getByName(String name) {
        try {
            return actionAdapter.getByName(name);
        }
        catch (Exception e) {
            return new ErrorService(
                "No se obtuvo la acción", 
                e.getMessage(), 
                this.myClassName
            );
        }
    }
    
    public Object getByModule_id(Integer module_id){
        try {
            return actionAdapter.getByModulet_id(module_id);
        }
        catch (Exception e) {
            return new ErrorService(
                "No se obtuvieron acciones", 
                e.getMessage(), 
                this.myClassName
            );
        }
    }

    public Object save(ActionDto actionDto ){
        try {
            return actionAdapter.save(actionDto);
        }
        catch (Exception e) {
            return new ErrorService(
                "No se pudo guardar la acción", 
                e.getMessage(), 
                this.myClassName
            );
        }
    }

    public Object delete(Integer id) {
        try {
            return actionAdapter.delete(id);
        }
        catch(Exception e){
            return new ErrorService(
                "No se eliminó la acción", 
                e.getMessage(), 
                this.myClassName
            );
        }
    }
    
}

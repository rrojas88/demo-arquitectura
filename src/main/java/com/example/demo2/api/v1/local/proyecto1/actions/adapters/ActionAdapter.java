
package com.example.demo2.api.v1.local.proyecto1.actions.adapters;

import com.example.demo2.api.v1.local.Utils.ErrorService;
import com.example.demo2.api.v1.local.proyecto1.actions.adapters.bd1.Action1;
import com.example.demo2.api.v1.local.proyecto1.actions.adapters.bd1.ActionRepository1;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ActionAdapter {
    
    @Autowired
    ActionRepository1 actionRepository;
    
    private String myClassName = ActionAdapter.class.getName();
    
    public Object getAll(){
        try {
            return actionRepository.findAll();
        }
        catch (Exception e) {
            return new ErrorService(
                "No se listaron las acciones.", 
                e.getMessage(), 
                this.myClassName
            );
        }
    }
    
    public Object getById(Integer id){
        try {
            Optional<Action1> rowOptional = actionRepository.findById(id);
            if( ! rowOptional.isPresent() || rowOptional.isEmpty() )
                return new ErrorService(id.toString(), "", this.myClassName, 404 );
            return rowOptional;
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
            return actionRepository.findByName(name);
        }
        catch (Exception e) {
            return new ErrorService(
                "No se obtuvo la acción.", 
                e.getMessage(), 
                this.myClassName
            );
        }
    }
    
    public Object getByModule_id(Integer module_id){
        try {
            return actionRepository.findAllByModuleId(module_id);
        }
        catch (Exception e) {
            return new ErrorService(
                "No se obtuvieron acciones.", 
                e.getMessage(), 
                this.myClassName
            );
        }
    }
    
    public Object getByModule_idAndCode(Integer module_id, String code){
        try {
            return actionRepository.findAllByModuleIdAndCode(module_id, code);
        }
        catch (Exception e) {
            return new ErrorService(
                "No se obtuvieron acciones.", 
                e.getMessage(), 
                this.myClassName
            );
        }
    }
    
    public Object save(ActionDto actionDto ){
        try {
            Action1 action = new Action1();
            if( actionDto.getId() != null ){
                action.setId( actionDto.getId() );
                action.setModule_id( actionDto.getModule_id() );
                action.setCode( actionDto.getCode() );
                action.setName( actionDto.getName() );
                action.setActive( actionDto.getActive() );
            }
            else{
                action.setModule_id( actionDto.getModule_id() );
                action.setCode( actionDto.getCode() );
                action.setName( actionDto.getName() );
            }
            return actionRepository.save(action);
        }
        catch (Exception e) {
            return new ErrorService(
                "No se pudo guardar la acción.", 
                e.getMessage(), 
                this.myClassName
            );
        }
    }

    public Object delete(Integer id) {
        try {
            Optional<Action1> row = actionRepository.findById(id);
            if( ! row.isEmpty() ){
                actionRepository.deleteById(id);
                return "Se eliminó el registro con ID: " + id;
            }
            return "No se encontró el registro con ID: " + id;
        }
        catch(Exception e){
            return new ErrorService(
                "No se eliminó la acción.", 
                e.getMessage(), 
                this.myClassName
            );
        }
    }
}

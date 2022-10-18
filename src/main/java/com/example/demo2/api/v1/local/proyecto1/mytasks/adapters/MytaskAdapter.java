
package com.example.demo2.api.v1.local.proyecto1.mytasks.adapters;

import com.example.demo2.api.v1.local.Utils.ErrorService;
import com.example.demo2.api.v1.local.proyecto1.mytasks.adapters.bd2.Mytask;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo2.api.v1.local.proyecto1.mytasks.adapters.bd2.MytaskRepository;


@Service
public class MytaskAdapter {

    @Autowired
    MytaskRepository mytasksRepository;
    

    private String myClassName = MytaskAdapter.class.getName();

    public Object getAll() {
        try {
            return mytasksRepository.findAll();
        } catch (Exception e) {
            return new ErrorService(
                "No se listaron las tareas.",
                e.getMessage(),
                this.myClassName
            );
        }
    }

    public Object getById(Integer id) {
        try {
            Optional<Mytask> rowOptional = mytasksRepository.findById(id);
            if (!rowOptional.isPresent() || rowOptional.isEmpty()) {
                return new ErrorService(id.toString(), "", this.myClassName, 404);
            }
            return rowOptional;
        } catch (Exception e) {
            return new ErrorService(
                "No se obtuvo la tarea",
                e.getMessage(),
                this.myClassName
            );
        }
    }

    public Object getByName(String name) {
        try {
            return mytasksRepository.findByName(name);
        } catch (Exception e) {
            return new ErrorService(
                "No se obtuvo la tarea",
                e.getMessage(),
                this.myClassName
            );
        }
    }

    public Object save(MytaskDto mytasksDto) {
        try {
            Mytask mytask = new Mytask();
            if( mytasksDto.getId() != null ){
                mytask.setId( mytasksDto.getId() );
                mytask.setName( mytasksDto.getName() );
                mytask.setActive( mytasksDto.getActive() );
            }
            else{
                mytask.setName( mytasksDto.getName() );
            }
            return mytasksRepository.save(mytask);
        } catch (Exception e) {
            return new ErrorService(
                "No se pudo guardar la tarea",
                e.getMessage(),
                this.myClassName
            );
        }
    }

    public Object delete(Integer id) {
        try {
            Optional<Mytask> row = mytasksRepository.findById(id);
            if( ! row.isEmpty() ){
                mytasksRepository.deleteById(id);
                return "Se eliminó el registro con ID: " + id;
            }
            return "No se encontró el registro con ID: " + id;
        } catch (Exception e) {
            return new ErrorService(
                "No se eliminó la tarea",
                e.getMessage(),
                this.myClassName
            );
        }
    }
}

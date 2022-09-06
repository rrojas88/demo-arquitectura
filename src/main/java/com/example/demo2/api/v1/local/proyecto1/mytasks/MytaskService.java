
package com.example.demo2.api.v1.local.proyecto1.mytasks;

import com.example.demo2.api.v1.local.Utils.ErrorService;
import com.example.demo2.api.v1.local.proyecto1.mytasks.adapters.MytaskAdapter;
import com.example.demo2.api.v1.local.proyecto1.mytasks.adapters.MytaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MytaskService {

    @Autowired
    MytaskAdapter mytasksAdapter;

    private String myClassName = MytaskService.class.getName();

    public Object getAll() {
        try {
            return mytasksAdapter.getAll();
        } catch (Exception e) {
            return new ErrorService(
                "No se listaron las tareas",
                e.getMessage(),
                this.myClassName
            );
        }
    }

    public Object getById(Integer id) {
        try {
            return mytasksAdapter.getById(id);
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
            return mytasksAdapter.getByName(name);
        } catch (Exception e) {
            return new ErrorService(
                "No se obtuvo la tarea",
                e.getMessage(),
                this.myClassName
            );
        }
    }

    public Object save(MytaskDto mytasks) {
        try {
            return mytasksAdapter.save(mytasks);
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
            return mytasksAdapter.delete(id);
        } catch (Exception e) {
            return new ErrorService(
                "No se eliminó la tarea",
                e.getMessage(),
                this.myClassName
            );
        }
    }
}

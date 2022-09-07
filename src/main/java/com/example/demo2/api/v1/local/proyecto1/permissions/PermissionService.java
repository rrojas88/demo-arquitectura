
package com.example.demo2.api.v1.local.proyecto1.permissions;

import com.example.demo2.api.v1.local.Utils.ErrorService;
import com.example.demo2.api.v1.local.proyecto1.actions.ActionService;
import com.example.demo2.api.v1.local.proyecto1.permissions.adapters.PermissionAdapter;
import com.example.demo2.api.v1.local.proyecto1.permissions.adapters.PermissionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PermissionService {

    @Autowired
    PermissionAdapter permissionAdapter;
    
    @Autowired
    ActionService actionService;

    private String myClassName = PermissionService.class.getName();

    public Object getAll() {
        try {
            return permissionAdapter.getAll();
        } catch (Exception e) {
            return new ErrorService(
                "No se listaron los permisos",
                e.getMessage(),
                this.myClassName
            );
        }
    }

    public Object getById(Integer id) {
        try {
            return permissionAdapter.getById(id);
        } catch (Exception e) {
            return new ErrorService(
                "No se obtuvo el permiso",
                e.getMessage(),
                this.myClassName
            );
        }
    }
/*
    public Object getByAction_id(Integer action_id) {
        try {
            return permissionAdapter.getByAction_id(action_id);
        } catch (Exception e) {
            return new ErrorService(
                "No se obtuvo el permiso",
                e.getMessage(),
                this.myClassName
            );
        }
    }

    public Object getByRol_id(Integer rol_id) {
        try {
            return permissionAdapter.getByRol_id(rol_id);
        } catch (Exception e) {
            return new ErrorService(
                "No se obtuvo el permiso",
                e.getMessage(),
                this.myClassName
            );
        }
    } */

    public Object save(PermissionDto permission) {
        try {
            return permissionAdapter.save(permission);
        } catch (Exception e) {
            return new ErrorService(
                "No se pudo guardar el permiso",
                e.getMessage(),
                this.myClassName
            );
        }
    }

    public Object delete(Integer id) {
        try {
            return permissionAdapter.delete(id);
        } catch (Exception e) {
            return new ErrorService(
               "No se eliminó el permiso",
                e.getMessage(),
                this.myClassName
            );
        }
    }
}

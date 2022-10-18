
package com.example.demo2.api.v1.local.proyecto1.permissions.adapters;

import com.example.demo2.api.v1.local.Utils.ErrorService;
import com.example.demo2.api.v1.local.proyecto1.permissions.adapters.bd1.Permission;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo2.api.v1.local.proyecto1.permissions.adapters.bd1.PermissionRepository;


@Service
public class PermissionAdapter {

    @Autowired
    PermissionRepository permissionRepository;

    private String myClassName = PermissionAdapter.class.getName();

    public Object getAll() {
        try {
            return permissionRepository.findAll();
        } catch (Exception e) {
            return new ErrorService(
                "No se listaron los permisos.",
                e.getMessage(),
                this.myClassName
            );
        }
    }

    public Object getById(Integer id) {
        try {
            Optional<Permission> rowOptional = permissionRepository.findById(id);
            if (!rowOptional.isPresent() || rowOptional.isEmpty()) {
                return new ErrorService(id.toString(), "", this.myClassName, 404);
            }
            return rowOptional;
        } catch (Exception e) {
            return new ErrorService(
                "No se obtuvo el permiso",
                e.getMessage(),
                this.myClassName
            );
        }
    }

    public Object getByAction_id(Integer action_id) {
        try {
            return permissionRepository.findAllByActionId(action_id);
        } catch (Exception e) {System.out.println("\n*  *  * Error PermissionAdapter:"); System.out.println(e.getMessage());
            return new ErrorService(
                "No se obtuvo el permiso",
                e.getMessage(),
                this.myClassName
            );
        }
    }
    /*
    public Object getByRol_id(Integer rol_id) {
        try {
            return permissionRepository.findByRol_id(rol_id);
        } catch (Exception e) {
            return new ErrorService(
                "No se obtuvo el permiso",
                e.getMessage(),
                this.myClassName
            );
        }
    } */

    public Object save(PermissionDto permissionDto) {
        try {
            Permission permission = new Permission();
            if( permissionDto.getId() != null ){
                permission.setId( permissionDto.getId() );
                permission.setAction_id(permissionDto.getAction_id());
                permission.setRol_id(permissionDto.getRol_id());
            }
            else{
                permission.setAction_id(permissionDto.getAction_id());
                permission.setRol_id(permissionDto.getRol_id());
            }
            return permissionRepository.save(permission);
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
            Optional<Permission> row = permissionRepository.findById(id);
            if (!row.isEmpty()) {
                permissionRepository.deleteById(id);
                return "Se eliminó el registro con ID: " + id;
            }
            return "No se encontró el registro con ID: " + id;
        } catch (Exception e) {
            return new ErrorService(
                "No se eliminó el permiso",
                e.getMessage(),
                this.myClassName
            );
        }
    }
}

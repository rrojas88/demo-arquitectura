
package com.example.demo2.api.v1.local.proyecto1.permissions;

import com.example.demo2.api.v1.local.Utils.ErrorService;
import com.example.demo2.api.v1.local.Utils.ResponseLocal;
import com.example.demo2.api.v1.local.Utils.UtilsLocal;
import com.example.demo2.api.v1.local.Utils.UtilsPermissions;
import com.example.demo2.api.v1.local.proyecto1.actions.ActionService;
import com.example.demo2.api.v1.local.proyecto1.actions.adapters.bd1.Action1;
import com.example.demo2.api.v1.local.proyecto1.auth.UserRolesPrincipal;
import com.example.demo2.api.v1.local.proyecto1.modules.ModuleService;
import com.example.demo2.api.v1.local.proyecto1.modules.adapters.bd1.Module;
import com.example.demo2.api.v1.local.proyecto1.permissions.adapters.PermissionAdapter;
import com.example.demo2.api.v1.local.proyecto1.permissions.adapters.PermissionDto;
import com.example.demo2.api.v1.local.proyecto1.permissions.adapters.bd1.Permission;
import com.example.demo2.api.v1.local.proyecto1.roles.RolService;
import com.example.demo2.api.v1.local.proyecto1.roles.adapters.bd1.Rol;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;


@Service
public class PermissionService {

    @Autowired
    PermissionAdapter permissionAdapter;
    
    @Autowired
    ModuleService moduleService;
    
    @Autowired
    ActionService actionService;
    
    @Autowired
    RolService rolService;

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
    
        
    public Object validate( HttpServletRequest req, String module_code, String action_code, ResponseLocal response )
    {
        try {
            Object mod = moduleService.getByCode(module_code);
            //System.out.println("\n*  *  * Module:"); System.out.println(mod);
            if( mod == null || ((ArrayList)mod).size() == 0 ){
                response.setError( 403, "No autorizado", "No autorizado",  UtilsLocal.emptyErrorList(), 
                    this.myClassName, "Modulo="+module_code+", Accion="+action_code, req);
                return response;
            }
            Integer module_id =  ( (Module) ((ArrayList)mod).get(0) ).getId(); 
            
            Object act = actionService.getByModule_idAndCode(module_id, action_code);
            System.out.println("\n*  *  * Permiso-Action:"); System.out.println(act);
            if( act == null || ((ArrayList)act).size() == 0 ){
                response.setError( 403, "No autorizado", "No autorizado",  UtilsLocal.emptyErrorList(), 
                    this.myClassName, "Modulo="+module_code+", Accion="+action_code, req);
                return response;
            }
            
            Integer action_id = ( (Action1) ((ArrayList)act).get(0) ).getId();
            
            // Roles que pueden acceder a esa "acción"
            Object actionRoles = permissionAdapter.getByAction_id(action_id);
           // System.out.println("\n*  *  * actionRoles:"); System.out.println(actionRoles);
            if( actionRoles == null || ((ArrayList)actionRoles).size() == 0 ){
                response.setError( 403, "No autorizado", "No autorizado",  UtilsLocal.emptyErrorList(), 
                    this.myClassName, "Modulo="+module_code+", Accion="+action_code, req);
                return response;
            }
            ArrayList action_roles = (ArrayList) actionRoles;
            
            // Roles del usuario
            UserRolesPrincipal userData = UtilsPermissions.getUserData();
            Collection<? extends GrantedAuthority> roles_ = userData.getAuthorities();
            Object[] user_roles = roles_.toArray();
            
            boolean exitsRolOk = false;
            for( int i=0; i < user_roles.length; i++ ){
                if( ! exitsRolOk )
                {
                    Optional<Rol> rolOptional = ( Optional<Rol> ) rolService.getByName(user_roles[ i ].toString());
                    if( rolOptional.isPresent() )
                    {
                        Rol rol = rolOptional.get();
                        Integer rolId = rol.getId();
                        for( int j=0; j < action_roles.size(); j++ ){
                            Permission perm = (Permission) action_roles.get( j );
                            System.out.println( rolId + " == " + perm.getRol_id() );
                            if( rolId == perm.getRol_id() ){
                                exitsRolOk = true;
                            }
                        }
                    } 
                }
            }
            
            if( ! exitsRolOk ){
                response.setError( 403, "No autorizado", "No autorizado",  UtilsLocal.emptyErrorList(), 
                    this.myClassName, "Modulo="+module_code+", Accion="+action_code, req);
                return response;
            }
            return null;
        }
        catch (Exception e) {
            System.out.println("\n*  *  * Error Validate Permission:"); System.out.println(e.getMessage());
            return new ErrorService(
                "No se pudo validar el permiso.",
                e.getMessage(),
                this.myClassName
            );
        }
    }
    
    
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
/*
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

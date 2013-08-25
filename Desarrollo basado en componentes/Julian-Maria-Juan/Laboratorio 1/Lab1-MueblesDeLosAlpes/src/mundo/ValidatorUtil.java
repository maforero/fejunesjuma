/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mundo;

/**
 *
 * @author maria
 */
public class ValidatorUtil {
    
    public static boolean isNullOrEmpty(Object o){
        if(o==null)
            return true;
        if(o instanceof String){
            if(o.equals(""))
                return true;
        }
        return false;
    }
}

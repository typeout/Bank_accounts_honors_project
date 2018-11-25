
package business;

/**
 *
 * @author Domas Butrimavicius
 */
public class Checking extends AssetAccount {
    public static final String TYPECD = "CK";
    public static final String TYPEDESC = "Checking Account";
    
    public Checking(){
        super();
    }
    
    public Checking(String nm, double sbal, String acttype) {
        super(nm, sbal, acttype);
    }
    
    public Checking(String fn, String a) {
        super(fn, a);
    }
    
    @Override
    public void setInterest(double ir){
     super.setActionMsg("Interest request: No action - checking accounts do not earn interest");
     super.writelog(super.getActionMsg());
    }

    @Override
    public String getTypeCd() {
        return Checking.TYPECD;
    }
    
    @Override
    public String getTypeDesc() {
        return Checking.TYPEDESC;
    }
    
}//end of checking

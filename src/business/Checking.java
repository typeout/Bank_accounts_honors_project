
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
    public void setCharge(double amt, String desc) {
        super.setErrMsg("");
        super.setActionMsg("");
        double balance = super.getBalance();

        if (super.getAcctNo() <= 0) {
            super.setErrMsg("Charge attempt on non-active account.");
            return;
        }
        
        if (amt <= 0) {
           super.setActionMsg("Charge of " + c.format(amt) + " for " + desc + " declined - illegal amount not positive. ");
           super.writelog(super.getActionMsg());
        } else if( amt > super.getBalance()) {
           super.setActionMsg("Charge of " + c.format(amt) + " for " + desc + " declined - insufficeint funds "); 
           super.writelog(super.getActionMsg());
        } else {
           super.setBalance(balance - amt);
           writestatus();
           if (super.getErrMsg().isEmpty()) {
               super.setActionMsg("Charge of " + c.format(amt) + " for " + desc + " posted.");
               super.writelog(super.getActionMsg());
           }else {
               super.setBalance(balance); //back out operation
           }
        }
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

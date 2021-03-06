
package business;

import java.text.NumberFormat;

/**
 *
 * @author Domas Butrimavicius
 */
public class Savings extends AssetAccount {
    public static final String TYPECD = "SV";
    public static final String TYPEDESC = "Passbook Savings";
    
    public Savings(){
        super();
    }
    
    public Savings(String nm, double sbal, String acttype){
        super(nm, sbal, acttype);
    }
    
    public Savings(String fn, String a) {
        super(fn, a);
    }
    
    @Override
    public void setInterest(double ir){
       double intearn, balance;
       NumberFormat p = NumberFormat.getPercentInstance();
       NumberFormat c = NumberFormat.getCurrencyInstance();
       
       if (super.getAcctNo() <= 0) {
           super.setErrMsg("Interest attempt on non-active account.");
           return;
       }
       
       if (ir <= 0 || ir > 1.0) {
           super.setActionMsg("Interest rate of " + p.format(ir) + 
                   " declined - rate not positive. or too large "); 
           super.writelog(super.getActionMsg());
       } else {
           intearn = super.getBalance() * ir/12.00;
           balance = super.getBalance();
           super.setBalance(balance + intearn);
           super.writestatus();
           if (super.getErrMsg().isEmpty()) {
               super.setActionMsg("Interest earned = " + c.format(intearn) + " for " +
                       "month at annual rate of: " + p.format(ir));
               super.writelog(super.getActionMsg());
           }else {
               super.setBalance(balance); //back out operation
           }
       } //end of interest charge method
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
        return Savings.TYPECD;
    }
    
    @Override
    public String getTypeDesc() {
        return Savings.TYPEDESC;
    }

}//end of savings

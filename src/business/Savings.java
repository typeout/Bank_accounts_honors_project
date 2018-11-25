
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
       double intearn;
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
           super.setBalance(intearn);
           super.writestatus();
           if (super.getErrMsg().isEmpty()) {
               super.setActionMsg("Interest earned = " + c.format(intearn) + " for " +
                       "month at annual rate of: " + p.format(ir));
               super.writelog(super.getActionMsg());
           }else {
               super.setBalance(-intearn); //back out operation
           }
       } //end of interest charge method
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

package business;

/**
 *
 * @author Domas Butrimavicius
 */
public interface Account {
    public int getAcctNo();
    public String getName();
    public double getBalance();
    public void setCharge(double amt, String desc);
    public void setPayment(double amt);
    public void setInterest(double rate);
    public String getErrMsg();
    public String getActionMsg();
    public String getTypeCd();
    public String getTypeDesc();
}

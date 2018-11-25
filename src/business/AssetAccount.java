package business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Domas Butrimavicius
 */
public abstract class AssetAccount implements Account {
    private int AcctNo;
    private double balance;
    private String actmsg, errmsg, nm;
    NumberFormat c = NumberFormat.getCurrencyInstance();
    
    public AssetAccount() {
        this.AcctNo = 0;
        this.actmsg = "";
        this.errmsg = "";
        this.balance= 0;
    }
    
    public AssetAccount (String nm, double sbal, String acttype){
        this.AcctNo = 0;
        this.actmsg = "";
        this.errmsg = "";
        this.balance= 0;
        
         while (this.AcctNo == 0) {
            try {
                this.AcctNo = (int) (Math.random() * 1000000);
                BufferedReader in = new BufferedReader(
                        new FileReader(acttype + this.AcctNo + ".txt"));
                in.close();
                this.AcctNo = 0;
            } catch (IOException e) {
                //'good' result: account does not yot exist....
                this.nm = nm;
                this.balance = sbal;
                writestatus();
                if (this.errmsg.isEmpty()) {
                   actmsg = acttype + " Account " +
                            this.nm + " " + this.AcctNo + " opened.";
                   writelog(actmsg);
                }   
                if (!this.errmsg.isEmpty()) {
                    this.balance = 0;
                    this.AcctNo = -1;
                }
            } catch (Exception e) {
                errmsg = "Fatal error in "+acttype+" Account constructor: " + e.getMessage();
                this.AcctNo = -1;
            }
        }//end of while
    }
    
    public AssetAccount (String fn, String a) {
        this.AcctNo = 0;
        this.actmsg = "";
        this.errmsg = "";
        this.balance= 0;
        
        try {
            this.AcctNo = Integer.parseInt(a);
            
            try {
                BufferedReader in = new BufferedReader(new FileReader(fn));
                this.nm = in.readLine();
                this.balance = Double.parseDouble(in.readLine());
                
                if (this.errmsg.isEmpty()) {
                   this.actmsg = getTypeCd() + " Account " +
                            this.nm + " " + this.AcctNo + " re-opened.";
                   writelog(this.actmsg);
                }
                
            } catch (IOException e) {
                this.errmsg = "File Open error: " + e.getMessage();
            }
            
            
        } catch (Exception e) {
            this.errmsg = "Wrong account number.";
        }
    }
    
    protected void writestatus() {
        try {
            PrintWriter out = new PrintWriter(
                    new FileWriter(getTypeCd() + this.AcctNo + ".txt"));
            out.println(this.nm);
            out.println(this.balance);
            out.close();
        } catch (IOException e) {
            errmsg = "Error writing status file for "
                    + " Savings account: "+ this.AcctNo;
        } catch(Exception e) {
            errmsg = "General error in Savings status update: " + e.getMessage();
        }
    }
    
    protected void writelog(String msg) {
        try {
            Calendar cal = Calendar.getInstance();
            DateFormat df = DateFormat.getDateTimeInstance();
            String ts = df.format(cal.getTime());
            PrintWriter out = new PrintWriter(
                              new FileWriter(getTypeCd() + "L" +
                                      this.AcctNo + ".txt",true));
            out.println(ts + ": " + msg);
            out.close();
        } catch (IOException e) {
            errmsg = "Error writing log file for " +
                    "Checking account " + this.AcctNo + e.getMessage();
        } catch (Exception e) {
            errmsg = "General error in write log: " + e.getMessage();
        }
    }
    
    @Override
    public int getAcctNo(){
        return this.AcctNo;
    }
    
    @Override
    public String getName(){
        return this.nm;
    }
    
    @Override
    public double getBalance(){
        return this.balance;
    }
    
    @Override
    public String getErrMsg(){
        return this.errmsg;
    }
    
    @Override
    public String getActionMsg(){
        return this.actmsg;
    }
         
    @Override
    public void setPayment(double amt) {
        errmsg = "";
        actmsg = "";
        
        if (this.AcctNo <= 0) {
            errmsg = "Deposit attempt on non-active account.";
            return;
        }
        
        if (amt <= 0) {
            actmsg = "Payment of " + c.format(amt) + 
                    " declined - amount must be positive.";
            writelog(actmsg); 
        } else {
            this.balance  += amt;
            writestatus();
            if (this.errmsg.isEmpty()) {
                actmsg = "Deposit of " + c.format(amt) + " posted.";
                writelog(actmsg);
            }else {
                this.balance -= amt;
            }
        }
    }
    
    @Override
    public ArrayList<String> getLog() {
        ArrayList<String> log = new ArrayList<>();
        this.errmsg = "";
        this.actmsg = "";
        
        if (this.AcctNo <= 0) {
            this.errmsg = "Log requested for non-active account.";
            return null;
        }
        
        try {
            BufferedReader in = new BufferedReader(new FileReader(getTypeCd() + "L" + this.AcctNo+".txt"));
            String s = in.readLine();
            while (s != null) {
                log.add(s);
                s = in.readLine();
            }
            in.close();
            this.actmsg = "Log returned for account.";
        } catch (Exception e) {
            this.errmsg = "Log file error: "+e.getMessage();
            return null;
        }
        return log;
    }
    
    @Override
    public abstract String getTypeCd();
    @Override
    public abstract String getTypeDesc();
    
    protected void setActionMsg(String msg) {
        this.actmsg = msg;
    }
    
    protected void setErrMsg(String msg) {
        this.errmsg = msg;
    }
    
    protected void setBalance(double num) {
        this.balance = num;
    }
}

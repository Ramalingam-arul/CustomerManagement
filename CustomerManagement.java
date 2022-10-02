/* GUI Application for ABC Corporation to collect and store the data of their customers. 
   Array List to store customer details and Add, Search and Display into Text Area */


   import java.awt.*;
   import java.awt.event.*;
   import javax.swing.*;
   import javax.swing.event.*;
   import java.util.*;
   import java.io.*;
   
   // Class Definition for Customer
   class Customer implements java.io.Serializable
   {
       // Members of Class - Customer Details
       String customerName;
       String customerNo;
       String city;
       String state;
       String pincode;
   }
   
   // Class Definition that holds the Swing appln
   class CustomerManagement extends JFrame implements ActionListener 
   {
       int n=0;
   
       // Creating Components on the Frame
       ArrayList<Customer> CustRec =new ArrayList<Customer>();
       JLabel labCustname, labCustno, labCity, labState, labPincode, labAddress, labDetails;
       JButton butAdd, butSearch, butDisplay ;
       JTextField txtCustname, txtCustno, txtCity, txtState, txtPincode ;
       JTextArea txtarDetails ;
       JScrollPane jsp;
   
       // Constructor for class
       public CustomerManagement() 
       {
           JFrame jframe = new JFrame("ABC Corporation Customer Interface");
           jframe.setSize(920, 800);
           jframe.setLayout(null);
   
           // X Button for closing the frame
           jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
           // Initializing all Buttons, Textfields, Textareas and Labels 
           labCustno = new JLabel("Customer No:");
              labCustno.setBounds(50,150,200,30);
              
              txtCustno = new JTextField();
              txtCustno.setBounds(300,150,200,30);
      
              labCustname = new JLabel("Customer Name");
              labCustname.setBounds(50, 200, 200, 30);
          
              txtCustname = new JTextField();
              txtCustname.setBounds(300, 200, 200, 30);
              
              labAddress = new JLabel("Address");
              labAddress.setBounds(50, 350, 200, 30);
              
              labCity = new JLabel("City");
              labCity.setBounds(50, 400, 200, 30);
              
              txtCity = new JTextField();
              txtCity.setBounds(300, 400, 200, 30);
              
              labState = new JLabel("State");
              labState.setBounds(50, 450, 200, 30);
          
              txtState = new JTextField();
              txtState.setBounds(300, 450, 200, 30);
          
              labPincode = new JLabel("Pincode");
              labPincode.setBounds(50, 500, 200, 30);
          
              txtPincode = new JTextField();
              txtPincode.setBounds(300, 500, 200, 30);
      
              labDetails = new JLabel("Details of Customer");
              labDetails.setBounds(50, 550, 200, 30);
              
              txtarDetails = new JTextArea();
              txtarDetails.setEditable(false);
              txtarDetails.setBounds(300, 550, 300, 150);
   
   
           // Adding JLabel and JTextField to the JFrame
           jframe.add(labCustno);
           jframe.add(txtCustno);
   
           jframe.add(labCustname);
           jframe.add(txtCustname);
   
           jframe.add(labAddress);
   
           jframe.add(labCity);
           jframe.add(txtCity);
   
           jframe.add(labState);
           jframe.add(txtState);
   
           jframe.add(labPincode);
           jframe.add(txtPincode);
   
           jframe.add(labDetails);
           jframe.add(txtarDetails);
   
           // Buttons initialization 
           butAdd = new JButton("ADD Customer");
           butAdd.setBounds(600, 200, 200, 30);
           // ActionListener which is a callback mechanism added to fire an ActionEvent
           butAdd.addActionListener(this);
           
           butSearch = new JButton("SEARCH Customer");
           butSearch.setBounds(600, 250, 200, 30);
           butSearch.addActionListener(this);
   
           butDisplay = new JButton("DISPLAY Customer");
           butDisplay.setBounds(600, 300, 200, 30);
           butDisplay.addActionListener(this);
   
           // Adding JButton to the frame
           jframe.add(butAdd);
           jframe.add(butSearch);
           jframe.add(butDisplay);
   
           // method to Display the Frame to the user 
           jframe.setVisible(true);
           jsp=new JScrollPane(jframe);
           add(jframe);
           setSize(920, 800);
           setVisible(true);
       }
   
   
       // Method to add Cutomer object to the Cutomer ArrayList
       public Customer customerRecordAddList(){
           // Temporary Customer class object to take in Details entered in the Frame
           Customer temp = new Customer();
           temp.customerNo = txtCustno.getText();
           temp.customerName = txtCustname.getText();
           temp.city = txtCity.getText();
           temp.state = txtState.getText();
           temp.pincode = txtPincode.getText();
   
           return temp;
       }
   
   
       // Method to clear the textfields
       void clear(){
           txtCustno.setText("");
           txtCustname.setText("");
           txtCity.setText("");
           txtState.setText("");
           txtPincode.setText("");
       }
   
       // Method to add New Customer to the Customer ArrayList
       void addCustomer(Customer obj)
       {
           boolean flag=false;
           //searching for duplicates in arraylist
        for(Customer c:CustRec)
        {
            if(c.customerNo.equals(txtCustno.getText()))
            {
                flag=true;
            }
        }
        if(flag==false)
        {
            //adding in arraylist
           CustRec.add(obj);
           clear();
           txtarDetails.setText("Customer Details Added to Log");
        }
        else
        {
            txtarDetails.setText("Customer number is Already in Log");
        }
       }
   
       // Method to search for a cutomer based on customer number
       //	if Customer found in entered list it is Added to File
       void searchCustomer()
       {
           Customer temp = new Customer();
           boolean flag = false;
           //for each loop search
           for(Customer c:CustRec)
           {
               if(c.customerNo.equals(txtCustno.getText()))
               {
                   flag = true;
                   try
                   {
                       // Opening file and adding details
                       FileOutputStream fos = new FileOutputStream("customerdetails.txt");
                       ObjectOutputStream myfile = new ObjectOutputStream(fos);
                       myfile.writeObject(c.customerName);
                       myfile.close();
                       txtarDetails.setText("Customer Number Found and added to File");
                       n++;
   
                   }catch(Exception io)
                   {
                       System.out.println(io);
                   
                   }
               }
           }
           if(flag == false){
               txtarDetails.setText("Customer Number not Found in Record");
           }
       }
   
       // Method to display a customer name from file for the searched customer number;
       void displayCustomer()
       {
           txtarDetails.setText("");
           boolean cont = true;
           String str2;
           try{
               //reading file
               FileInputStream fos = new FileInputStream("customerdetails.txt");
               ObjectInputStream myfile = new ObjectInputStream(fos);
                   for(int i=0;i<n;i++)
                   {
                       str2=(String)myfile.readObject();
                       txtarDetails.append("customer name is "+str2);
                   }
                   
               }
           catch(Exception e)
           {
               System.out.println(e);
           }
       }
   
       // Method to Handle Button Actions
       public void actionPerformed(ActionEvent ae) 
       {
   
           if (ae.getSource() == butAdd){
               addCustomer(customerRecordAddList());
           }
           else if(ae.getSource() == butSearch){
               searchCustomer();
           }
           else if(ae.getSource() == butDisplay){
               displayCustomer();
           }
       }
   
       public static void main(String args[]) 
       {
           // Common to all // 
   
           SwingUtilities.invokeLater(new Runnable() 
           {
   
               public void run() 
               {
   
                   new CustomerManagement();
               }
           }); 
   
       }
   }
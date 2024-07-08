import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RegisterForm extends JFrame{
	private static final long serialVersionUID = 1L;
	private JLabel lblWelcomeText;
	private JLabel lblName;
	private JLabel lblSurName;
	private JLabel lblTcKimlik;
	private JLabel lblBirthday;
	private JLabel lblPassword;
	private JLabel lblPassword2;
	private JLabel lblSecurityQuestion;
	private JLabel lblEmpty;
	private JLabel lblEmpty2;
	private JTextField tfName;
	private JTextField tfSurName;
	private JTextField tfTcKimlik;
	private JTextField tfSecurityQuestionAnswer;
	private JComboBox<String> cmbGender;
	private JCheckBox cbShowPassword;
	private JPasswordField pfPassword;
	private JPasswordField pfPassword2;
	private JPanel pnlTop;
	private JPanel pnlBottom;
	private JPanel pnlBirthDay;
	private JPanel pnlShowPassword;
	private JPanel pnlBottomButtons;
	private JButton btnRegister;
	private JButton btnReturnLogin;
	private BorderLayout frameBorderLayout;
	private GridLayout pnlTopGridLayout;
	private GridLayout pnlBottomGridLayout;
	private GridLayout pnlBirthDayGridLayout;
	private FlowLayout cbShowPasswordFlowLayout;
	private FlowLayout pnlBottomButtonsFlowLayout;
    private MaskFormatter dateFormatter = null;
    private JFormattedTextField birthdayField = null;
	
	public RegisterForm(final LoginForm lf) {
		
		Container cp = getContentPane();
		frameBorderLayout = new BorderLayout();
		setLayout(frameBorderLayout);
		
		lblWelcomeText = new JLabel("Patient Appointment System", SwingConstants.CENTER);
		Font text = new Font("Courier", Font.BOLD, 25);
		lblWelcomeText.setPreferredSize(new Dimension (3, 100));
		lblWelcomeText.setFont(text);
		cp.add(lblWelcomeText, BorderLayout.NORTH);

		lblEmpty = new JLabel("        ");
		cp.add(lblEmpty, BorderLayout.EAST);

		lblEmpty2 = new JLabel("        ");
		cp.add(lblEmpty2, BorderLayout.WEST);
		
		pnlTop = new JPanel();
		pnlTopGridLayout = new GridLayout(7, 2, 3, 3);
		pnlTop.setLayout(pnlTopGridLayout);
		
		lblName = new JLabel("NAME: ", SwingConstants.CENTER);
		Font text1 = new Font("Courier", Font.BOLD, 13);
		lblName.setFont(text1);
		pnlTop.add(lblName);
		tfName = new JTextField();
		pnlTop.add(tfName);
		
		lblSurName = new JLabel("SURNAME: ", SwingConstants.CENTER);
		lblSurName.setFont(text1);
		pnlTop.add(lblSurName);
		tfSurName = new JTextField();
		pnlTop.add(tfSurName);
		
		lblTcKimlik = new JLabel("TR-ID: ", SwingConstants.CENTER);
		lblTcKimlik.setFont(text1);
		pnlTop.add(lblTcKimlik);
		
		tfTcKimlik = new JTextField();
		pnlTop.add(tfTcKimlik);
		
		lblBirthday = new JLabel("BIRHDAY(mm-dd-yyyy) & GENDER:", SwingConstants.CENTER);
		lblBirthday.setFont(text1);
		pnlTop.add(lblBirthday);
		
		pnlBirthDay = new JPanel();
		pnlBirthDayGridLayout = new GridLayout(1, 2, 3, 3);
		pnlBirthDay.setLayout(pnlBirthDayGridLayout);
		
		try {
            dateFormatter = new MaskFormatter("##-##-####");
            birthdayField = new JFormattedTextField(dateFormatter);
            birthdayField.setColumns(10);
            birthdayField.setHorizontalAlignment(JTextField.CENTER);            
            pnlBirthDay.add(birthdayField);
        } 
		
		catch (ParseException e) {
            e.printStackTrace();
        } 
		
		catch (NullPointerException e) {
            e.printStackTrace();
        }
		
		String[] gender = {"Select Gender", "Male", "Female"};
 		cmbGender = new JComboBox<String>(gender);
 		pnlBirthDay.add(cmbGender);
 		pnlTop.add(pnlBirthDay);
		
		lblPassword = new JLabel("PASSWORD: ", SwingConstants.CENTER);
		lblPassword.setFont(text1);
		pnlTop.add(lblPassword);
		pfPassword = new JPasswordField();
		pfPassword.setEchoChar('●');
		pnlTop.add(pfPassword);
		
		lblPassword2 = new JLabel("CONFIRM PASSWORD: ", SwingConstants.CENTER);
		lblPassword2.setFont(text1);
		pnlTop.add(lblPassword2);
		pfPassword2 = new JPasswordField();
		pfPassword2.setEchoChar('●');
		pnlTop.add(pfPassword2);
		
		lblSecurityQuestion = new JLabel("What's your mother's maiden name?", SwingConstants.CENTER);
		lblSecurityQuestion.setFont(text1);
		pnlTop.add(lblSecurityQuestion);
		tfSecurityQuestionAnswer = new JTextField();
		pnlTop.add(tfSecurityQuestionAnswer);

		cp.add(pnlTop, BorderLayout.CENTER);
		
		pnlBottom = new JPanel();
		pnlBottomGridLayout = new GridLayout(3, 1, 3, 3);
		pnlBottom.setLayout(pnlBottomGridLayout);
		
		pnlShowPassword = new JPanel();
		cbShowPasswordFlowLayout = new FlowLayout(FlowLayout.CENTER, 3, 3);
		pnlShowPassword.setLayout(cbShowPasswordFlowLayout);
		
		cbShowPassword = new JCheckBox("Show Password");
		CbShowPasswordListener cbShowPasswordListener = new CbShowPasswordListener();
        cbShowPassword.addItemListener(cbShowPasswordListener);
        
        pnlShowPassword.add(cbShowPassword);
        pnlBottom.add(pnlShowPassword);
		
		pnlBottomButtons = new JPanel();
		pnlBottomButtonsFlowLayout = new FlowLayout(FlowLayout.CENTER, 3, 3);
		pnlBottomButtons.setLayout(pnlBottomButtonsFlowLayout);
		
		btnRegister = new JButton("Register");
		BtnRegisterListener regListener = new BtnRegisterListener(lf);
		btnRegister.addActionListener(regListener);
		
		TCCheckListener tcListener = new TCCheckListener();
		tfTcKimlik.addKeyListener(tcListener);
		
		NameSurNameListener nameSurNameListener = new NameSurNameListener();
		tfName.addKeyListener(nameSurNameListener);
		tfSurName.addKeyListener(nameSurNameListener);
		
		PassCheckListener passListener = new PassCheckListener();
		pfPassword.addKeyListener(passListener);
		pfPassword2.addKeyListener(passListener);
		pnlBottomButtons.add(btnRegister);
		
		btnReturnLogin = new JButton("Return Login");
		BtnReturnLoginListener rtrnLoginListener= new BtnReturnLoginListener(lf);
		btnReturnLogin.addActionListener(rtrnLoginListener);
		pnlBottomButtons.add(btnReturnLogin);
		
		pnlBottom.add(pnlBottomButtons);
		cp.add(pnlBottom, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Register to continue");
        setResizable(false);
		setSize(640, 525);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private class BtnRegisterListener implements ActionListener {
		private LoginForm lf;
		
		public BtnRegisterListener(LoginForm l) {
			lf = l;
		}
		
		@Override
		public void actionPerformed(ActionEvent evt) {
			BufferedReader br = null;
			FileReader fr = null;
			int tempID = 0;
			boolean isFound = false;
			boolean isInformed = false;
			String password = new String(pfPassword.getPassword());
			String password2 = new String(pfPassword2.getPassword());
			
			ArrayList<String> listPasswords = new ArrayList<>();
		    br = null;
		    fr = null;
        	listPasswords.add(tfTcKimlik.getText().trim());
		    
		    try {
		        fr = new FileReader("notAllowedPasswords.txt");
		        br = new BufferedReader(fr);
		        String line;
		        String[] strArray;
		        
		        while ((line = br.readLine()) != null) {
		            strArray = line.split(",");
		            
		            if (strArray.length == 2) {
		            	listPasswords.add(strArray[1]);
		            }
		        }
		    } 
		    
		    catch (IOException ex) {
				System.out.println("There was a problem reading the file.");
		        JOptionPane.showMessageDialog(lf, "There was a problem reading the file.");
		    } 
		    
		    finally {
		    	
		        if (fr != null) {
		        	
		            try {
		                fr.close();
		            } 
		            
		            catch (IOException exp) {
						System.out.println("The file read but not closed.");
		            }
		        }
		    }
		    
		    String[] notAcceptedPasswords = listPasswords.toArray(new String[0]);
			
			try {
		        File usersFile = new File("users.txt");
		        
		        if (!usersFile.exists()) {
		        	usersFile.createNewFile();
		        }
		        
		        else if (usersFile.exists() && usersFile.length() == 0) {
					if (tfName.getText().trim().isEmpty() || tfSurName.getText().trim().isEmpty() 
							|| tfTcKimlik.getText().trim().isEmpty() || !isValidDate(birthdayField.getText().trim())
							|| cmbGender.getSelectedItem().equals("Select Gender") || password.trim().isEmpty()
							|| password2.trim().isEmpty() || tfSecurityQuestionAnswer.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "You can not continue the process without filling in all fields."
								+ "\nOr make sure you fill in the blanks correctly.","ERROR : Unfilled Fields", JOptionPane.ERROR_MESSAGE);
						isInformed = true;
					}
		        }
		        
				fr = new FileReader(usersFile);
				br = new BufferedReader(fr);
				String line;
				String[] strArray;

				while ((line = br.readLine()) != null) {
				    strArray = line.split(",");
				    tempID = Integer.parseInt(strArray[0]);
				    
					if (tfName.getText().trim().isEmpty() || tfSurName.getText().trim().isEmpty() 
							|| tfTcKimlik.getText().trim().isEmpty() || !isValidDate(birthdayField.getText().trim())
							|| cmbGender.getSelectedItem().equals("Select Gender") || password.trim().isEmpty()
							|| password2.trim().isEmpty() || tfSecurityQuestionAnswer.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "You can not continue the process without filling in all fields."
								+ "\nOr make sure you fill in the blanks correctly.","ERROR : Unfilled Fields", JOptionPane.ERROR_MESSAGE);
						isInformed = true;
						break;
					}
					
					else if (strArray.length == 8) {
				    	if (tfTcKimlik.getText().trim().equals(strArray[3].trim())) {
					        isFound = true;
					        JOptionPane.showMessageDialog(lf, "Already registered with this TR-ID. \nIf you forgot your password, click Forgot Password.",
									"ERROR : Registered TR-ID", JOptionPane.ERROR_MESSAGE);
					        break;
				    	}
				    }
				    
				    else {
				    	isFound = false;
				    	break;
				    }
				}
			}
			
			catch (IOException e) {
				System.out.println("There was a problem reading the file.");
		        JOptionPane.showMessageDialog(lf, "There was a problem reading the file.");
			}
			
			finally {
				
				if(fr != null) {
					try {
						fr.close();
					}
					
					catch (IOException exp){
						System.out.println("There was a problem closing the file.");
				        JOptionPane.showMessageDialog(lf, "There was a problem closing the file.");
					}
				}
			}
			
			if (isInformed == false && isFound == false) {
					
				if ((tfTcKimlik.getText().length() == 11) && (tfTcKimlik.getText().charAt(0) != '0')
						&& (Character.getNumericValue(tfTcKimlik.getText().charAt(10)) % 2 == 0)) {
					
					FileWriter fw = null;
					
					try {
						tempID++;
						boolean isPasswordAccepted = true;
							
						if (password.trim().equals(password2.trim())) {

							if (password.length() > 4) {
								for (String i : notAcceptedPasswords) {
									
									if (i.equals(password)) {
										isPasswordAccepted = false;
								        JOptionPane.showMessageDialog(lf, "You can not enter a simple password like \"123456\" or one that contains personal information!",
								        		"ERROR : Basic Password", JOptionPane.ERROR_MESSAGE);
								        break;
									}
								}
									
								if (isPasswordAccepted) {
									String temp = tempID + "," + tfName.getText().trim() + "," + tfSurName.getText().trim() + "," 
											+ tfTcKimlik.getText().trim() + "," + password.trim() + "," 
											+ tfSecurityQuestionAnswer.getText().trim() + "," + cmbGender.getSelectedItem() + ","
											+ birthdayField.getText().trim() + "\r\n";
									fw = new FileWriter("users.txt", true);
									fw.write(temp);
									System.out.println(tfName.getText().trim() + " " + tfSurName.getText().trim() + " has registered successfully.");
							        JOptionPane.showMessageDialog(lf, "You have registered successfully.");
							        dispose();
									lf.setVisible(true);
								}
							}
							
							else {
						        JOptionPane.showMessageDialog(lf, "You can not enter password shorter than 5 characters.",
						        		"ERROR : Insufficient Password", JOptionPane.ERROR_MESSAGE);
						        isInformed = true;
							}
						}
							
						else {
					        JOptionPane.showMessageDialog(lf, "The entered passwords do not match each other.",
			        		"ERROR : Different Passwords", JOptionPane.ERROR_MESSAGE);
						}
					}
					
					catch (IOException e) {
				        JOptionPane.showMessageDialog(lf, "You could not register.");
					}
					
					finally {
						if (fw != null) {
							try {
								fw.close();
							}
							
							catch (IOException exp) {
								System.out.println("There was a problem closing the file.");
						        JOptionPane.showMessageDialog(lf, "There was a problem closing the file.");
							}
						}
					}
				}
				
				else {
			        JOptionPane.showMessageDialog(lf, "Please enter a valid Turkish Republic Identity Number.\n● TR-ID must consist of 11 digits."
			        		+ "\n● TR-ID can not start with '0' (Zero)." + "\n● TR-ID can not end with an odd number.",
			        		"ERROR : Invalid Password", JOptionPane.ERROR_MESSAGE);
		
				}
			}
		}
	}
	
	private class TCCheckListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent evt) {
			char key = evt.getKeyChar();
			if (!((Character.isDigit(key) || (key == KeyEvent.VK_BACK_SPACE) || (key == KeyEvent.VK_DELETE)) && (tfTcKimlik.getText().length() < 11))) {
				evt.consume();
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	}
	
	private class NameSurNameListener implements KeyListener {
	    @Override
	    public void keyTyped(KeyEvent evt) {
	        char key = evt.getKeyChar();
	        if (!Character.isLetter(key) && !(key == KeyEvent.VK_BACK_SPACE) && !(key == KeyEvent.VK_DELETE) && !(key == KeyEvent.VK_SPACE)) {
	            evt.consume();
	        }
	    }

		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	}
	
	private class PassCheckListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent evt) {
			char key = evt.getKeyChar();
			if (((key == KeyEvent.VK_COMMA) || (key == KeyEvent.VK_SPACE)) && (pfPassword.getPassword().length < 16)) {
				evt.consume();
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
		
	}
	
	private class BtnReturnLoginListener implements ActionListener {
		private LoginForm lf;
		
		public BtnReturnLoginListener(LoginForm l) {
			lf = l;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			lf.setVisible(true);
		}
	}
	
	private class CbShowPasswordListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                pfPassword.setEchoChar((char) 0);
                pfPassword2.setEchoChar((char) 0);
            } 
            
            else {
                pfPassword.setEchoChar('●');
                pfPassword2.setEchoChar('●');
            }
		}
	}
	public static boolean isValidDate(String dateStr) {
		
        try {
            String[] parts = dateStr.split("-");
            int month = Integer.parseInt(parts[0]);
            int day = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);

            if (month < 1 || month > 12 || day < 1 || year < 1900 || year > LocalDate.now().getYear()) {
                return false;
            }

            Calendar calendar = Calendar.getInstance();
            calendar.setLenient(false);
            calendar.set(year, month - 1, 1);
            calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            
            if (day > calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                return false;
            }

            @SuppressWarnings("unused")
			Date date = calendar.getTime();
            return true;
        } 
        
        catch (Exception e) {
            return false;
        }
    }
}

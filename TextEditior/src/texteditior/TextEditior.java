
package texteditior;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class TextEditior implements ActionListener,KeyListener{

    JTextArea text;
    Container c;
    JMenuBar menue;
    JMenu file,compiler,font;
    JMenuItem save,open,save_as,exit,python,c_languge,serif;
    JFrame frame;
    File fileToSave=null;
    JFileChooser fc=new JFileChooser();
    JList<String> font_name;
    public TextEditior()
    {
        frame=new JFrame();
        frame.setSize(1024, 728);
        frame.setTitle("Text Editor");
        
        text=new JTextArea();
        text.setRows(36);
        text.setColumns(122);
        text.setLineWrap(true);
        text.addKeyListener(this);
        
        JScrollPane scroll=new JScrollPane(text,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.updateUI();
        FlowLayout f=new FlowLayout();
        frame.setLayout(f);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        c=frame.getContentPane();
        
        menue=new JMenuBar();
        
        file=new JMenu("File");
        save=new JMenuItem("Save");
        save.addActionListener(this);
        save_as=new JMenuItem("Save as");
        save_as.addActionListener(this);
        open=new JMenuItem("Open");
        open.addActionListener(this);
        exit=new JMenuItem("Exit");
        exit.addActionListener(this);
        
        compiler=new JMenu("compiler");
        python=new JMenuItem("python");
        python.addActionListener(this);
        c_languge=new JMenuItem("C  Language");
        c_languge.addActionListener(this);
        
        font=new JMenu("Front");
        serif=new JMenuItem("Serif");
        serif.addActionListener(this);
        //save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        
        frame.setJMenuBar(menue);
        menue.add(file);
        
        file.add(save);
        file.add(open);
        file.add(save_as);
        file.add(exit);
       
        menue.add(compiler);
        compiler.add(python);
        compiler.add(c_languge);
        
        menue.add(font);
        font.add(serif);
        
        //c.add(text);
        c.add(scroll);
        
        
        frame.setVisible(true);
        
       
        
    }
    
    

        
    

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==save)
       {
            JFileChooser fc=new JFileChooser();
            fc.setDialogTitle("save");
            int userSelection=fc.showSaveDialog(frame);
            
            if(userSelection==JFileChooser.APPROVE_OPTION)
            {
                fileToSave=fc.getSelectedFile();
                try {
                    FileWriter writer = new FileWriter(fileToSave.getAbsolutePath());
           
                    writer.write(text.getText());
                    writer.close();
                } 
                catch (IOException ex)
                {
           
                }
            }
       }
       if(e.getSource()==open)
       {
           JFileChooser fc=new JFileChooser();
            fc.setDialogTitle("Open");
            int userSelection=fc.showOpenDialog(frame);
            if(userSelection==JFileChooser.APPROVE_OPTION)
            {
                fileToSave=fc.getSelectedFile();
                try
                {
                    FileReader reader=new FileReader(fileToSave.getAbsoluteFile());
                    int ch;
                    StringBuffer sc=new StringBuffer();
                    while((ch=reader.read())!=-1)
                    {
                        sc.append((char)ch);
                    }
                    reader.close();
                    text.setText(sc.toString());
                }catch(IOException obj)
                {
                    
                }
                
            }
       }
       
       if(e.getSource()==save_as)
       {
           //File fileToSave=fc.getSelectedFile();
            JFileChooser fc=new JFileChooser();
            
            fc.setDialogTitle("save as");
            //int userSelection=fc.showOpenDialog(frame);
            if(fileToSave!=null)
            {
                fc.setSelectedFile(fileToSave.getAbsoluteFile());
                int userSelection=fc.showSaveDialog(frame);
                fileToSave=fc.getSelectedFile();
                try {
                    FileWriter writer = new FileWriter(fileToSave.getAbsolutePath());
           
                    writer.write(text.getText());
                    writer.close();
                } 
                catch (IOException ex)
                {
           
                }
            }
            else
            {
                File new_file=new File("*.txt");
                fc.setSelectedFile(new_file);
                int userSelection=fc.showSaveDialog(frame);
                fileToSave=fc.getSelectedFile();
                try {
                    FileWriter writer = new FileWriter(fileToSave.getAbsolutePath());
           
                    writer.write(text.getText());
                    writer.close();
                } 
                catch (IOException ex)
                {
           
                }
            }   
       }
       
       if(e.getSource()==exit)
       {
           System.exit(0);
       }
       
       if(e.getSource()==python)
       {
           if(fileToSave!=null)
           {
               String file_name=fileToSave.getName();
               int index=file_name.lastIndexOf('.');
               if(index>0)
               {
                   String extension=file_name.substring(index+1);
                   if(extension.equalsIgnoreCase("py"))
                   {
                       cmd("\"python -u "+fileToSave.getAbsolutePath());
                   }
                   else
                   {
                       JOptionPane.showMessageDialog(null,"Please Check the File Extension");
                   }
               }
               
           }
           else
           {
               JOptionPane.showMessageDialog(null,"Please Save The file");
           }
           
       }
       if(e.getSource()==c_languge)
       {
           if(fileToSave!=null)
           {
               String file_name=fileToSave.getName();
               int index=file_name.lastIndexOf('.');
               if(index>0)
               {
                   String extension=file_name.substring(index+1);
                   if(extension.equalsIgnoreCase("c"))
                   {
                       //gcc C:\Users\pc6\gcd.c && a.exe
                       cmd("gcc "+fileToSave.getAbsolutePath()+" && a.exe");
                   }
                   else
                   {
                       JOptionPane.showMessageDialog(null,"Please Check the File Extension");
                   }
               
               
               }
               
           }
           else
           {
               JOptionPane.showMessageDialog(null,"Please Save The file");
           }
           
       }
       
       if(e.getSource()==serif)
       {
           JFrame font_frame=new JFrame();
           font_frame.setVisible(true);
           font_frame.setTitle("Font");
           font_frame.setSize(440, 475);
           font_frame.setResizable(false);
           font_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
           font_frame.setBounds(300, 200, 440,475);
           font_frame.setLayout(null);
    
           DefaultListModel<String> fonts=new DefaultListModel<>();
           font_name=new JList<>(fonts);
           font_name.setBounds(20, 80, 170, 140);
           font_frame.add(font_name);
           fonts.addElement("Serif");
           
           Font f=new Font(Font.SANS_SERIF,Font.ROMAN_BASELINE,30);
           font_frame.setFont(f);
           
           
           
           
           
       }
        
    }
    public void cmd(String cmd_input)
    {
       // ProcessBuilder builder=new ProcessBuilder("cmd.exe","/c","c:\\Users\\pc6\\rohan.py");
       try
       {
           //Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"python -u \"c:\\Users\\pc6\\rohan.py\"");
           Runtime.getRuntime().exec("cmd /c start cmd.exe /K \""+cmd_input);
       }
       catch(Exception obj)
       {
           System.out.println("error");
       }
        
    }
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch (Exception ex) {
        }
        new TextEditior();
    }

    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
         
       if((e.getKeyCode()==KeyEvent.VK_S)&&((e.getModifiers()&KeyEvent.CTRL_MASK)!=0))
       {
           
           if(fileToSave!=null)
           {
               fc.setSelectedFile(fileToSave.getAbsoluteFile());
               fileToSave=fc.getSelectedFile();
                try {
                    FileWriter writer = new FileWriter(fileToSave.getAbsolutePath());
           
                    writer.write(text.getText());
                    writer.close();
                    JOptionPane.showMessageDialog(null,"File Saved");
                } 
                catch (IOException ex)
                {
           
                }
            }
           else
           {
            fc.setDialogTitle("save");
            int userSelection=fc.showSaveDialog(frame);
            
            if(userSelection==JFileChooser.APPROVE_OPTION)
            {
                fileToSave=fc.getSelectedFile();
                try {
                    FileWriter writer = new FileWriter(fileToSave.getAbsolutePath());
           
                    writer.write(text.getText());
                    writer.close();
                } 
                catch (IOException ex)
                {
           
                }
            }
           }
      }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}

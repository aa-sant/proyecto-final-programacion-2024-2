package vista;

import javax.swing.*;

import controlador.Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private JFrame FrameIni;  
    private JFrame FramePrinc;  
    private JFrame Frameproducto;
    
    private Controlador c;

    public GUI(Controlador c) {
    	this.c = c;
        InicioSsPant();
    }
    
    private void InicioSsPant() {
        FrameIni = new JFrame("Iniciar Sesión");
        FrameIni.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrameIni.setSize(600, 300);
        FrameIni.setLayout(null);

        JLabel Lbidenti = new JLabel("Ingrese su identificación:");
        Lbidenti.setBounds(120, 50, 200, 30);
        FrameIni.add(Lbidenti);

        JTextField Fieldidenti = new JTextField(20);
        Fieldidenti.setBounds(100, 90, 200, 30);
        FrameIni.add(Fieldidenti);

        JButton IniSesBoton = new JButton("Iniciar Sesión");
        IniSesBoton.setBounds(90, 150, 150, 30);
        FrameIni.add(IniSesBoton);

        JButton RegisBoton = new JButton("Registrarse");
        RegisBoton.setBounds(210, 150, 150, 30);
        FrameIni.add(RegisBoton);

        IniSesBoton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	FrameIni.dispose();  
            	CategoriasPant();  
            }
        });
        RegisBoton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	FrameIni.dispose();  
            	CategoriasPant();  
            }
        });
        FrameIni.setResizable(false);
        FrameIni.setLocationRelativeTo(null);
        FrameIni.setVisible(true);
    }
    
    
    
    
    
    private void CategoriasPant() {
        FramePrinc = new JFrame("PlusMarket - Categorías");
        FramePrinc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FramePrinc.setSize(800, 600);
        FramePrinc.setLocationRelativeTo(null);
        FramePrinc.setResizable(false);

        FramePrinc.setLayout(null);

        JLabel LbCategorias = new JLabel("Seleccione una categoría:");
        LbCategorias.setBounds(50, 30, 200, 30);
        FramePrinc.add(LbCategorias);

        JButton BotonComida = new JButton("ALIMENTOS");
        BotonComida.setBounds(50, 80, 150, 30);
        FramePrinc.add(BotonComida);

        JButton BotonBebida = new JButton("BEBIDAS");
        BotonBebida.setBounds(50, 130, 150, 30);
        FramePrinc.add(BotonBebida);

        JButton BotonAseo = new JButton("ASEO");
        BotonAseo.setBounds(50, 180, 150, 30);
        FramePrinc.add(BotonAseo);

        JButton electrodomesticosButton = new JButton("ELECTRODOMÉSTICOS");
        electrodomesticosButton.setBounds(50, 230, 150, 30);
        FramePrinc.add(electrodomesticosButton);

        JButton serviciosButton = new JButton("SERVICIOS");
        serviciosButton.setBounds(50, 280, 150, 30);
        FramePrinc.add(serviciosButton);

        JButton cartButton = new JButton("Ver Carrito");
        cartButton.setBounds(50, 330, 150, 30);
        FramePrinc.add(cartButton);

        
   
      
        BotonComida.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	MostrarProduc("ALIMENTOS");
            }
        });
        BotonBebida.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	MostrarProduc("BEBIDAS");
            }
        });
        
        BotonAseo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	MostrarProduc("ASEO");            }
        });
        electrodomesticosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	MostrarProduc("ELECTRODOMÉSTICOS");           }
        });
        
        serviciosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	MostrarProduc("SERVICIOS");          }
        });
        
//
        FramePrinc.setVisible(true);
    }

    private void MostrarProduc(String category) {
        FramePrinc.setVisible(false);  

        Frameproducto = new JFrame("PlusMarket - " + category);
        Frameproducto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Frameproducto.setSize(600, 400);
        Frameproducto.setLocationRelativeTo(null);
        Frameproducto.setResizable(false);

        Frameproducto.setLayout(null);  

        JLabel productLabel = new JLabel("Productos de la categoría: " + category);
        productLabel.setBounds(50, 30, 300, 30);
        Frameproducto.add(productLabel);

        JComboBox<String> productComboBox = new JComboBox<>(new String[]{"Producto 1", "Producto 2", "Producto 3"});
        productComboBox.setBounds(50, 80, 200, 30);
        Frameproducto.add(productComboBox);

        JLabel quantityLabel = new JLabel("Cantidad:");
        quantityLabel.setBounds(50, 130, 100, 30);
        Frameproducto.add(quantityLabel);

        JTextField quantityField = new JTextField(5);
        quantityField.setBounds(120, 130, 50, 30);
        Frameproducto.add(quantityField);

        JButton addToCartButton = new JButton("Añadir al Carrito");
        addToCartButton.setBounds(50, 180, 150, 30);
        addToCartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (productComboBox.getSelectedItem() == null || quantityField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(Frameproducto, "Seleccione un producto e ingrese la cantidad.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(Frameproducto, "Producto añadido al carrito");
                }
            }
        });
        Frameproducto.add(addToCartButton);

        JButton backButton = new JButton("Volver");
        backButton.setBounds(50, 230, 150, 30);
        backButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
            	Frameproducto.dispose();  
                FramePrinc.setVisible(true);  
            }
        });
        Frameproducto.add(backButton);

        Frameproducto.setVisible(true);
    }
}

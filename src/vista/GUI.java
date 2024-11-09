package vista;
import javax.swing.*;

import controlador.Controlador;
import controlador.GestorCarrito;
import controlador.GestorProductos;
import modelo.ItemCarrito;
import modelo.Producto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
public class GUI {
    private JFrame FrameIni;  
    private JFrame FramePrinc;  
    private JFrame Frameproducto;
    private JFrame FramePago;
    
    private Controlador c;

    public GUI(Controlador c) {
    	this.c = c;
        InicioSsPant();
    }
   
    private void InicioSsPant() {
        FrameIni = new JFrame("Iniciar Sesión");
        FrameIni.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrameIni.setSize(400, 250);  
        FrameIni.setLayout(new GridLayout(4, 1, 10, 10)); 

        JLabel Lbidenti = new JLabel("Ingrese su identificación:", SwingConstants.CENTER);
        FrameIni.add(Lbidenti);

        JTextField Fieldidenti = new JTextField(20);
        Fieldidenti.setHorizontalAlignment(JTextField.CENTER); 
        FrameIni.add(Fieldidenti);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));  
        JButton IniSesBoton = new JButton("Iniciar Sesión");
        buttonPanel.add(IniSesBoton);

        JButton RegisBoton = new JButton("Registrarse");
        buttonPanel.add(RegisBoton);

        FrameIni.add(buttonPanel); 

        IniSesBoton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String identificacion = Fieldidenti.getText();
                if (!identificacion.isEmpty()) {
                    String mensaje = c.iniciarSesion(identificacion);  
                    JOptionPane.showMessageDialog(FrameIni, mensaje);
                    if (mensaje.equals("Sesión iniciada correctamente.")) {
                        FrameIni.dispose();
                        CategoriasPant();
                    }
                } else {
                    JOptionPane.showMessageDialog(FrameIni, "Por favor ingrese su identificación.");
                }
            }
        });

        RegisBoton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String identificacion = Fieldidenti.getText();
                if (!identificacion.isEmpty()) {
                    String nombre = JOptionPane.showInputDialog(FrameIni, "Ingrese su nombre:");
                    if (nombre != null && !nombre.trim().isEmpty()) {
                        String mensaje = c.registrarUsuario(identificacion, nombre.trim());
                        JOptionPane.showMessageDialog(FrameIni, mensaje);
                        if (mensaje.equals("Usuario registrado correctamente.")) {
                            FrameIni.dispose();
                            CategoriasPant();
                        }
                    } else {
                        JOptionPane.showMessageDialog(FrameIni, "Identificación inválida.");
                    }
                } else {
                    JOptionPane.showMessageDialog(FrameIni, "Por favor ingrese su identificación.");
                }
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

        JButton BtnCarro = new JButton("Ver Carrito");
        BtnCarro.setBounds(50, 330, 150, 30);
        FramePrinc.add(BtnCarro);
        BtnCarro.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		mostrarCarrito();
        	}
        });

        JButton BtnPagar = new JButton("Pagar");  
        BtnPagar.setBounds(50, 380, 150, 30);
        BtnPagar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarResumenCompra();
            }
        });
        FramePrinc.add(BtnPagar);

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
                MostrarProduc("ASEO");            
            }
        });
        electrodomesticosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MostrarProduc("ELECTRODOMÉSTICOS");           
            }
        });
        serviciosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MostrarProduc("SERVICIOS");          
            }
        });

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

        JLabel lbproducto = new JLabel("Productos de la categoría: " + category);
        lbproducto.setBounds(50, 30, 300, 30);
        Frameproducto.add(lbproducto);

        List<Producto> productos = GestorProductos.obtenerProductosPorCategoria(category);
        JComboBox<String> productComboBox = new JComboBox<>();
        for (Producto producto : productos) {
            productComboBox.addItem(producto.getNombre() + ". Precio: $" + producto.getPrecio());
        }
        productComboBox.setBounds(50, 80, 200, 30);
        Frameproducto.add(productComboBox);

        JLabel Cantidadlb = new JLabel("Cantidad:");
        Cantidadlb.setBounds(50, 130, 100, 30);
        Frameproducto.add(Cantidadlb);

        JSpinner CantidadSP = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        CantidadSP.setBounds(120, 130, 50, 30);
        Frameproducto.add(CantidadSP);

        JButton addToCartButton = new JButton("Añadir al Carrito");
        addToCartButton.setBounds(50, 180, 150, 30);
        addToCartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (productComboBox.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(Frameproducto, "Seleccione un producto.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String selectedProduct = productComboBox.getSelectedItem().toString();
                    Producto producto = GestorProductos.obtenerProductoPorItem(selectedProduct);

                    if (producto != null) {
                        int cantidad = (Integer) CantidadSP.getValue();
                        String mensaje = GestorCarrito.anadirProductoAlCarrito(producto, cantidad);
                        JOptionPane.showMessageDialog(Frameproducto, mensaje);
                    }
                }
            }
        });
        Frameproducto.add(addToCartButton);

        JButton BtnPagar = new JButton("Pagar");
        BtnPagar.setBounds(50, 230, 150, 30);
        BtnPagar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarResumenCompra();
            }
        });
        Frameproducto.add(BtnPagar);

        JButton volverBoton = new JButton("Volver");
        volverBoton.setBounds(50, 280, 150, 30);
        volverBoton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Frameproducto.dispose();
                FramePrinc.setVisible(true);
            }
        });
        Frameproducto.add(volverBoton);

        Frameproducto.setVisible(true);
    }

    private void mostrarResumenCompra() {
        JFrame FramePago = new JFrame("Resumen de Compra");
        FramePago.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        FramePago.setSize(500, 400);
        FramePago.setLocationRelativeTo(null);
        FramePago.setResizable(false);
        FramePago.setLayout(new BorderLayout());

        JPanel panelDetalleCompra = new JPanel(new GridLayout(0, 1));
        JLabel lblTitulo = new JLabel("Detalles de la Compra:", JLabel.CENTER);
        panelDetalleCompra.add(lblTitulo);

        List<ItemCarrito> itemsCarrito = GestorCarrito.getItems();
        float total = 0;

        for (ItemCarrito item : itemsCarrito) {
            Producto producto = item.getProducto();
            int cantidad = item.getCantidad();
            float precioTotal = producto.getPrecio() * cantidad;
            total += precioTotal;

            JLabel lblProducto = new JLabel(producto.getNombre() + " x" + cantidad + " - Precio Total: $" + precioTotal);
            panelDetalleCompra.add(lblProducto);
        }

        JLabel LbTT = new JLabel("Total: $" + total, JLabel.CENTER);
        panelDetalleCompra.add(LbTT);

        JButton btnConfirmar = new JButton("Confirmar Compra");
        btnConfirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(FramePago, "Compra confirmada!");
                FramePago.dispose();
            }
        });

        JButton btnvolver = new JButton("Volver a categorias");
        btnvolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FramePago.dispose();
            }
        });

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotones.add(btnConfirmar);
        panelBotones.add(btnvolver);

        FramePago.add(panelDetalleCompra, BorderLayout.CENTER);
        FramePago.add(panelBotones, BorderLayout.SOUTH);

        FramePago.setVisible(true);
    }
    private void mostrarCarrito() {
        JFrame frameCarrito = new JFrame("Carrito de Compras");
        frameCarrito.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameCarrito.setSize(600, 400);
        frameCarrito.setLocationRelativeTo(null);
        frameCarrito.setResizable(false);
        frameCarrito.setLayout(new BorderLayout());

        JPanel panelCarrito = new JPanel();
        panelCarrito.setLayout(new GridLayout(0, 1));

        List<ItemCarrito> itemsCarrito = GestorCarrito.getItems();

        if (itemsCarrito.isEmpty()) {
            JLabel lblVacio = new JLabel("El carrito está vacío.");
            panelCarrito.add(lblVacio);
        } else {
            for (ItemCarrito item : itemsCarrito) {
                Producto producto = item.getProducto();
                int cantidad = item.getCantidad();
                float precioTotal = producto.getPrecio() * cantidad;

                JLabel lblProducto = new JLabel(producto.getNombre() + " x" + cantidad + " - Precio Total: $" + precioTotal);
                panelCarrito.add(lblProducto);//uwu
            }
        }

        JButton btnVolver = new JButton("Volver a Categorías");
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameCarrito.dispose();
                FramePrinc.setVisible(true);  
            }
        });

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotones.add(btnVolver);

        frameCarrito.add(panelCarrito, BorderLayout.CENTER);
        frameCarrito.add(panelBotones, BorderLayout.SOUTH);

        frameCarrito.setVisible(true);}}
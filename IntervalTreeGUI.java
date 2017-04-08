
/**
 * For the gui things, if you want to learn more, then you could try to search "Java swing and awt" in Google.
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Button;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class IntervalTreeGUI<T extends Comparable<T>> {

	JFrame frame;
	private IntervalNode<T> troot;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		try {
			/* test the gui */
			IntervalADT<Integer> t = new Interval<Integer>(1, 2, "Test");
			IntervalNode<Integer> root = new IntervalNode<Integer>(t);
			root.setLeftNode(new IntervalNode<Integer>(t));
			root.setRightNode(new IntervalNode<Integer>(t));
			root.getLeftNode().setLeftNode(new IntervalNode<Integer>(t));
			root.getLeftNode().setRightNode(new IntervalNode<Integer>(t));
			root.getRightNode().setLeftNode(new IntervalNode<Integer>(t));

			IntervalTreeGUI<Integer> window = new IntervalTreeGUI<Integer>(root);
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public IntervalTreeGUI() {

	}

	/**
	 * Create the application with corresponding parameters.
	 * 
	 * @return
	 */
	public IntervalTreeGUI(IntervalNode<T> root) {
		troot = root;
		initialize(root);
		addPanel(root);
	}

	/**
	 * 
	 * @param root:
	 *            the root node that to display
	 */
	public void addNode(final IntervalNode<T> root) {
		Button backbutton = new Button("Back to root");
		backbutton.setBounds(100, 20, 100, 30);
		backbutton.setBackground(Color.red);
		frame.getContentPane().add(backbutton);
		backbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				addPanel(troot);
			}
		});
		String name;
		int len = 16;
		if (root != null) {
			if (root.getInterval().getLabel() == null) {
				name = null;
			} else
				name = root.getInterval().getLabel().substring(0,
						Math.min(root.getInterval().getLabel().length(), len));
			String st = String.valueOf(name + " [" + root.getInterval().getStart()) + "   " +
					String.valueOf(root.getInterval().getEnd() + "]   maxEnd: " + root.getMaxEnd());
			Button button = new Button(st);
			button.setBounds(350, 20, 200, 30);
			button.setBackground(Color.CYAN);
			frame.getContentPane().add(button);

			final IntervalNode<T> left = root.getLeftNode();
			final IntervalNode<T> right = root.getRightNode();
			if (left != null) {
				if (left.getInterval().getLabel() == null) {
					name = null;
				} else
					name = left.getInterval().getLabel().substring(0,
							Math.min(left.getInterval().getLabel().length(), len));
				st = String.valueOf(name + " [" + left.getInterval().getStart()) + "   " +
						String.valueOf(left.getInterval().getEnd() + "]   maxEnd: " + left.getMaxEnd());
				Button button_1 = new Button(st);
				button_1.setBounds(150, 170, 200, 30);
				button_1.setBackground(Color.CYAN);
				frame.getContentPane().add(button_1);
				button_1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						addPanel(left);
					}
				});
				final IntervalNode<T> lleft = left.getLeftNode();
				final IntervalNode<T> lright = left.getRightNode();
				if (lleft != null) {
					if (lleft.getInterval().getLabel() == null) {
						name = null;
					} else
						name = lleft.getInterval().getLabel().substring(0,
								Math.min(lleft.getInterval().getLabel().length(), len));
					st = String.valueOf(name + " [" + lleft.getInterval().getStart()) + "   " +
							String.valueOf(lleft.getInterval().getEnd() + "]   maxEnd: " + lleft.getMaxEnd());
					Button button_3 = new Button(st);
					button_3.setBounds(10, 360, 200, 30);
					button_3.setBackground(Color.CYAN);
					frame.getContentPane().add(button_3);
					button_3.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							addPanel(lleft);
						}
					});

				}
				if (lright != null) {
					if (lright.getInterval().getLabel() == null) {
						name = null;
					} else
						name = lright.getInterval().getLabel().substring(0,
								Math.min(lright.getInterval().getLabel().length(), len));
					st = String.valueOf(name + " [" + lright.getInterval().getStart()) + "   " +
							String.valueOf(lright.getInterval().getEnd() + "]   maxEnd: " + lright.getMaxEnd());
					Button button_4 = new Button(st);
					button_4.setBounds(220, 360, 200, 30);
					button_4.setBackground(Color.CYAN);
					frame.getContentPane().add(button_4);
					button_4.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							addPanel(lright);
						}
					});
				}
			}
			if (right != null) {
				if (right.getInterval().getLabel() == null) {
					name = null;
				} else
					name = right.getInterval().getLabel().substring(0,
							Math.min(right.getInterval().getLabel().length(), len));
				st = String.valueOf(name + " [" + right.getInterval().getStart()) + "   " +
						String.valueOf(right.getInterval().getEnd() + "]   maxEnd: " + right.getMaxEnd());
				Button button_2 = new Button(st);
				button_2.setBounds(550, 170, 200, 30);
				button_2.setBackground(Color.CYAN);
				frame.getContentPane().add(button_2);
				button_2.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						addPanel(right);
					}
				});
				final IntervalNode<T> rleft = right.getLeftNode();
				final IntervalNode<T> rright = right.getRightNode();
				if (rleft != null) {
					if (rleft.getInterval().getLabel() == null) {
						name = null;
					} else
						name = rleft.getInterval().getLabel().substring(0,
								Math.min(rleft.getInterval().getLabel().length(), len));
					st = String.valueOf(name + " [" + rleft.getInterval().getStart()) + "   " +
							String.valueOf(rleft.getInterval().getEnd() + "]   maxEnd: " + rleft.getMaxEnd());
					Button button_5 = new Button(st);
					button_5.setBounds(470, 360, 200, 30);
					button_5.setBackground(Color.CYAN);
					frame.getContentPane().add(button_5);
					button_5.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							addPanel(rleft);
						}
					});
				}
				if (rright != null) {
					if (rright.getInterval().getLabel() == null) {
						name = null;
					} else
						name = rright.getInterval().getLabel().substring(0,
								Math.min(rright.getInterval().getLabel().length(), len));
					st = String.valueOf(name + " [" + rright.getInterval().getStart()) + "   " +
							String.valueOf(rright.getInterval().getEnd() + "]   maxEnd: " + rright.getMaxEnd());
					Button button_6 = new Button(st);
					button_6.setBounds(680, 360, 200, 30);
					button_6.setBackground(Color.CYAN);
					frame.getContentPane().add(button_6);
					button_6.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							addPanel(rright);
						}
					});
				}
			}
		}

	}

	/**
	 * Simply, when we want to set a certain node as the root, we just rebuild
	 * the Jpanel.
	 * 
	 * @param root
	 */
	public void addPanel(final IntervalNode<T> root) {
		JPanel contentPane = new JPanel() {
			public void paintComponent(Graphics g) {
				try {
					/* set the background image */
					g.drawImage(ImageIO.read(new File("image/bg.jpg")), 0, 0, this.getWidth(), this.getHeight(), this);

					/* draw the corresponding line to connect the nodes */
					g.setColor(Color.CYAN);
					if (root != null) {
						IntervalNode<T> left = root.getLeftNode();
						IntervalNode<T> right = root.getRightNode();
						if (left != null) {
							g.drawLine(450, 35, 250, 185);
							IntervalNode<T> lleft = left.getLeftNode();
							IntervalNode<T> lright = left.getRightNode();
							if (lleft != null) {
								g.drawLine(250, 185, 110, 375);
							}
							if (lright != null) {
								g.drawLine(250, 185, 320, 375);
							}
						}
						if (right != null) {
							g.drawLine(450, 35, 650, 185);
							IntervalNode<T> rleft = right.getLeftNode();
							IntervalNode<T> rright = right.getRightNode();
							if (rleft != null) {
								g.drawLine(650, 185, 570, 375);
							}
							if (rright != null) {
								g.drawLine(650, 185, 780, 375);
							}
						}
					}
				} catch (IOException e) {
				}
			}
		};

		frame.setContentPane(contentPane);
		frame.getContentPane().setLayout(null);
		contentPane.revalidate();
		contentPane.repaint();
		addNode(root);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param root
	 */
	private void initialize(final IntervalNode<T> root) {
		/* set the gui title */
		frame = new JFrame("Simple Interval Tree GUI");
		/* set the gui icon */
		Image icon = Toolkit.getDefaultToolkit().getImage("image/icon.png");
		frame.setIconImage(icon);
		/* set the gui size */
		frame.setBounds(100, 100, 900, 600);

	}

	public JFrame getFrame() {
		return frame;
	}
}
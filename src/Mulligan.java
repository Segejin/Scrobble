
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Mulligan extends JFrame {
	private final JButton _redraw;
	private final JButton _keep;
	private static JLabel _redrawLabel;
	private static JLabel _keepLabel;
	private boolean _selection = false;
	private boolean _keeping = true;
	
	public Mulligan() {
		super("Mulligan");		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(new BorderLayout());
		
		_redraw = new JButton("Redraw");
		_redraw.setOpaque(true);
		_redraw.setPreferredSize(new Dimension(125, 30));
		_redraw.setAlignmentX(Mulligan.CENTER_ALIGNMENT);
		_redraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setKeeping();
				setSelection();
			}
		});
		
		_keep = new JButton("Keep");
		_keep.setOpaque(true);
		_keep.setPreferredSize(new Dimension(125, 30));
		_keep.setAlignmentX(Mulligan.CENTER_ALIGNMENT);
		_keep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSelection();
			}
		});
		
		_redrawLabel = new JLabel();
		_redrawLabel.setPreferredSize(new Dimension(20, 150));
		
		_keepLabel = new JLabel();
		_keepLabel.setPreferredSize(new Dimension(20, 150));
		
		JPanel mulliganButtonPanel = new JPanel();
		mulliganButtonPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.LINE_START;
        gbc.weighty = 0.25;
		mulliganButtonPanel.add(_redraw, gbc);
		gbc.gridy = 1;
		mulliganButtonPanel.add(_keep, gbc);
		
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        gbc.weighty = 1;
		labelPanel.add(_redrawLabel, gbc);
		gbc.gridy = 1;
		labelPanel.add(_keepLabel, gbc);
		
		add(mulliganButtonPanel, BorderLayout.WEST);
		add(labelPanel, BorderLayout.CENTER);
		
		setSize(150, 115);
	}
	
	public boolean getKeeping() {
		return _keeping;
	}
	
	private void setKeeping() {
		if(_keeping) {
			_keeping = false;
		} else {
			_keeping = true;
		}
	}
	
	public void toggleKeeping() {
		setKeeping();
	}
	
	public boolean getSelection() {
		return _selection;
	}
	
	private void setSelection() {
		_selection = true;
	}
}
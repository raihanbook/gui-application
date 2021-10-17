import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class QuizController {
	
	private int benar = 0;
	private int salah = 0;
	private int nilai = 0;
	private String prevPertanyaan = "";
	private String prevJawab = "";
	private String prevKunciJawaban = "";
	private int prevNilai = 0;
	private List<JRadioButton> prevButton = new ArrayList<JRadioButton>();
	private ButtonGroup buttonGroup = new ButtonGroup();
	private int index = 0;

	public QuizController() {
	
	}
	
	public void getSoal(ModelQuiz model, JFrame frame, QuizController controller) {
				
		if (index > model.getListSoal().size()-1) {
			getResult(frame);
		} else {
			String[] soal = model.getListSoal().get(index).split(";");

			switch (soal[0]) {
			case "multiple choice":
				JLabel pertanyaanMultiple = new JLabel(soal[1]);
				pertanyaanMultiple.setHorizontalAlignment(SwingConstants.CENTER);
				pertanyaanMultiple.setBounds(101, 65, 234, 13);
				frame.getContentPane().add(pertanyaanMultiple);
				
				prevKunciJawaban = soal[3];
				prevNilai = Integer.parseInt(soal[4]);
				prevPertanyaan = pertanyaanMultiple.getText();
				
				String[] pilihanMultiple = soal[2].split("#");
				int yCoor = 0;
				
				if (prevButton.size() > 0) {				
					prevButton.removeAll(prevButton);
				}
				
				for (String string : pilihanMultiple) {
					
					JRadioButton jawaban = new JRadioButton(string);
					buttonGroup.add(jawaban);
					jawaban.setBounds(101, 98 + yCoor, 234, 21);
					frame.getContentPane().add(jawaban);
					
					yCoor += 20;
					prevButton.add(jawaban);
				}
				
				JButton nextMultiple = new JButton("Next");
				nextMultiple.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.getContentPane().removeAll();
						index++;
						getJawaban(frame);
						controller.getSoal(model, frame, controller);
					}
				});
				nextMultiple.setBounds(341, 232, 85, 21);
				frame.getContentPane().add(nextMultiple);
				
				frame.repaint();
				break;
			case "essay":
				JLabel pertanyaanEssay = new JLabel(soal[1]);
				pertanyaanEssay.setHorizontalAlignment(SwingConstants.CENTER);
				pertanyaanEssay.setBounds(101, 65, 234, 13);
				frame.getContentPane().add(pertanyaanEssay);
				
				JTextField jawabanEssay = new JTextField();
				jawabanEssay.setBounds(172, 122, 96, 19);
				frame.getContentPane().add(jawabanEssay);
				jawabanEssay.setColumns(10);

				prevKunciJawaban = soal[2];
				prevNilai = Integer.parseInt(soal[3]);
				
				if (prevButton.size() > 0) {				
					prevButton.removeAll(prevButton);
				}

				JButton nextEssay = new JButton("Next");
				nextEssay.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						prevJawab = jawabanEssay.getText();
						prevPertanyaan = pertanyaanEssay.getText();
						
						frame.getContentPane().removeAll();
						index++;
						getJawaban(frame);
						controller.getSoal(model, frame, controller);
					}
				});
				nextEssay.setBounds(341, 232, 85, 21);
				frame.getContentPane().add(nextEssay);
				
				frame.repaint();
				break;
			default:
				break;
			}
		}
	}
	
	public void getJawaban(JFrame frame) {
		if (prevButton.size() > 0) {			
			for (JRadioButton button : prevButton) {
				if (button.isSelected()) {						
					if (button.getText().charAt(0) == prevKunciJawaban.charAt(0)) {
						JOptionPane.showMessageDialog(frame, "Benar");
						benar++;
						nilai += prevNilai;
					} else {						
						JOptionPane.showMessageDialog(frame, "Salah");
						salah++;
					}
				}
			}
		} else {
			if (prevJawab.equalsIgnoreCase(prevKunciJawaban)) {
				JOptionPane.showMessageDialog(frame, "Benar");
				benar++;
				nilai += prevNilai;
			} else {
				JOptionPane.showMessageDialog(frame, "Salah");
				salah++;
			}
		}
	}
	
	public void getResult(JFrame frame) {
		JOptionPane.showMessageDialog(frame, "Nilai = " + nilai + " salah = " + salah + " benar = " + benar);
	}
}

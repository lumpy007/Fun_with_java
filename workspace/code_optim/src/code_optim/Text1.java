package code_optim;

import java.awt.Color;
import java.awt.Font;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.Document;

public class Text1 extends JTextArea
{
	public List<File> gfile = new ArrayList<File>() ;
	public Text1() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Text1(Document doc, String text, int rows, int columns) {
		super(doc, text, rows, columns);
		// TODO Auto-generated constructor stub
	}

	public Text1(Document doc) {
		super(doc);
		// TODO Auto-generated constructor stub
	}

	public Text1(int rows, int columns) {
		super(rows, columns);
		// TODO Auto-generated constructor stub
	}

	public Text1(String text, int rows, int columns) {
		super(text, rows, columns);
		setBackground(new Color(168,187,192));
        setForeground(new Color(0,0,0));
        setFont(new Font("Nokia Pure Text", Font.PLAIN,16) );
		setDropTarget(new DropTarget() {
            public synchronized void drop(DropTargetDropEvent evt) {
                try {
                	
                    evt.acceptDrop(DnDConstants.ACTION_COPY);
                    List<File> droppedFiles = (List<File>) evt
                            .getTransferable().getTransferData(
                                    DataFlavor.javaFileListFlavor);
                    //gfile = droppedFiles;
                    setText("");
                    for (File file : droppedFiles) {
                        setText(getText()+file.getAbsolutePath()+"\n");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
		
	}

	public Text1(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}
	
	public void readFiles()
	{
		gfile.clear();
		String[] fileAbsolutePaths = null;
		
		fileAbsolutePaths = this.getText().split("\\r?\\n");
		for (String path : fileAbsolutePaths)
		{
			File file = new File(path);
			gfile.add(file);
		}
	}

	


}
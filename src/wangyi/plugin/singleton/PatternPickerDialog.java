package wangyi.plugin.singleton;

import wangyi.plugin.singleton.OnPatternSelectedListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * author WangYi
 * created on 2017/1/14.
 * 选择单例实现模式
 */
public class PatternPickerDialog extends JDialog {
    private JPanel mContentPane;
    private JButton mButtonOK;
    private JButton mButtonCancel;
    private JList mListPattern;

    private OnPatternSelectedListener mListener;

    public PatternPickerDialog() {
        setContentPane(mContentPane);
        setModal(true);
        getRootPane().setDefaultButton(mButtonOK);
        setTitle("Choose a Pattern");

        mButtonOK.addActionListener(e -> onOK());
        mButtonCancel.addActionListener(e -> onCancel());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        mContentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        mListPattern.setSelectedIndex(0);
        setBounds(new Rectangle(200, 200));
        setLocationRelativeTo(null);
    }

    private void onOK() {
        dispose();
        if (mListener != null) {
            mListener.onPatternSelected(mListPattern.getSelectedIndex());
        }
    }

    private void onCancel() {
        dispose();
    }

    public void setOnPatternSelectedListener(OnPatternSelectedListener mListener) {
        this.mListener = mListener;
    }
}

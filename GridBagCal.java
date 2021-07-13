import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;
import java.util.Arrays;

class GridBagCal extends JFrame implements ActionListener{
	private JButton buttons[];
	boolean ox = true;
	boolean re = true;
	String remember;
	JTextArea tf;
	int top;
	char[] stack;

	public GridBagCal(){

		super("전수현 11주차 계산기");
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints constraint = new GridBagConstraints();
		
		setLayout(new BorderLayout ());
		Panel p1 = new Panel();
		Panel p2 = new Panel();

		p2.setLayout(gridbag);

		Font font = new Font("Serif", Font.PLAIN,15);

		constraint.fill= GridBagConstraints.BOTH;
		tf = new JTextArea (1,20);//속성값을 집어넣어서 크기를 키우자!
		tf.setFont(font);
		p1.add(tf);

		buttons = new JButton[19];

		//레이아웃 설정을 그리드백으로 맞춤

		//그리드백 내부를 채우는데 양쪽 다 꽉 차게끔 채워라
		constraint.weightx=1.0;
		constraint.weighty=1.0;

		constraint.gridx =0;
		buttons[10] = new JButton("C");
		gridbag.setConstraints(buttons[10], constraint);
		p2.add(buttons[10]);
		
		constraint.gridx =1;
		constraint.gridy =0;
		buttons[11] = new JButton("/");
		gridbag.setConstraints(buttons[11],constraint);
		p2.add(buttons[11]);
		
		//현재 행의 마지막 값이다!
		constraint.gridx =2;
		buttons[12] = new JButton("X");
		gridbag.setConstraints(buttons[12],constraint);
		p2.add(buttons[12]);

		constraint.gridx =3;
		buttons[13] = new JButton("DEL");
		gridbag.setConstraints(buttons[13],constraint);
		p2.add(buttons[13]);

		
		constraint.gridx =0;
		constraint.gridy =1;
		buttons[7] = new JButton("7");
		gridbag.setConstraints(buttons[7], constraint);
		p2.add(buttons[7]);
		
		constraint.gridx =1;
		buttons[8] = new JButton("8");
		gridbag.setConstraints(buttons[8],constraint);
		p2.add(buttons[8]);
		
		//현재 행의 마지막 값이다!
		constraint.gridx =2;
		buttons[9] = new JButton("9");
		gridbag.setConstraints(buttons[9],constraint);
		p2.add(buttons[9]);


		constraint.gridx =3;
		buttons[14] = new JButton("-");
		gridbag.setConstraints(buttons[14],constraint);
		p2.add(buttons[14]);


		constraint.gridy =2;
		constraint.gridx =0;
		buttons[4] = new JButton("4");
		gridbag.setConstraints(buttons[4], constraint);
		p2.add(buttons[4]);
		
		constraint.gridx =1;
		buttons[5] = new JButton("5");
		gridbag.setConstraints(buttons[5],constraint);
		p2.add(buttons[5]);
		
		constraint.gridx =2;
		buttons[6] = new JButton("6");
		gridbag.setConstraints(buttons[6],constraint);
		p2.add(buttons[6]);


		constraint.gridx =3;
		buttons[15] = new JButton("+");
		gridbag.setConstraints(buttons[15],constraint);
		p2.add(buttons[15]);

		constraint.gridy =3;
		constraint.gridx =0;
		buttons[1] = new JButton("1");
		gridbag.setConstraints(buttons[1], constraint);
		p2.add(buttons[1]);
		
		constraint.gridx =1;
		buttons[2] = new JButton("2");
		gridbag.setConstraints(buttons[2],constraint);
		p2.add(buttons[2]);
		
		constraint.gridx =2;
		buttons[3] = new JButton("3");
		gridbag.setConstraints(buttons[3],constraint);
		p2.add(buttons[3]);


		constraint.gridx =3;
		buttons[16] = new JButton("()");
		gridbag.setConstraints(buttons[16],constraint);
		p2.add(buttons[16]);

		constraint.gridy =4;
		constraint.gridx =0;
		buttons[0] = new JButton("0");
		gridbag.setConstraints(buttons[0], constraint);
		p2.add(buttons[0]);
		
		constraint.gridx =1;
		buttons[17] = new JButton("R");
		gridbag.setConstraints(buttons[17],constraint);
		p2.add(buttons[17]);
		
		constraint.gridwidth=2;

		constraint.gridx =2;
		buttons[18] = new JButton("=");
		gridbag.setConstraints(buttons[18],constraint);
		p2.add(buttons[18]);

		add("North",p1);
		add(p2);
		pack();

		for(int i=0; i<buttons.length; i++){
			buttons[i].addActionListener(this);
		}

		setSize(300,300);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e){
		String[] list = new String[10];
		double result = 0;
		for(int i=0; i<buttons.length;i++){
			if(e.getSource()==buttons[i]){
				if(i<10){
					tf.append(Integer.toString(i));
				}
				else{
					switch(i){
							case 15://+
							tf.append(" + ");
							break;
							case 14://-
							tf.append(" - ");
							break;
							case 12://x
							tf.append(" * ");
							break;
							case 11:// /
							tf.append(" / ");
							break;
							case 17:// R
								
								if(re==true) {remember=tf.getText();
								re=false;System.out.println(remember);}
								else {re=true; tf.append(remember); }
								
								break;
							case 16 : // ()
							if(ox == true){
								tf.append("( ");
								ox=false;}
							else if(ox==false){
								tf.append(" ) ");
								ox=true;}
								break;
							case 18:// =
								String a = tf.getText();
								int wow=total(a);
								tf.setText(Integer.toString(wow));
								break;
							case 13: //del
								int t = tf.getText().length();
								if(t == 0)tf.setText("");
								else if(t>0){
									String b = tf.getText().substring(0,t-1);
									tf.setText(b);
								}
								break;
							case 10: //c
								tf.setText("");
								break;
						}	
							
						}
						
					}
				}
			}

	public int total(String str){
		String[] line = str.split(" ");
		String[] sb = new String[100];
		Stack<String> stack = new Stack<>();
		StringBuilder send = new StringBuilder();
		int i;
		for(i=0; i<line.length; i++){
			
		if(line[i].equals("*") || line[i].equals("/")){
			while(! stack.empty()&&(stack.peek().equals("*") || stack.peek().equals("/")))
				{
				sb[i]=stack.pop();
			}

			stack.push(line[i]);
		}else if(line[i].equals("+") || line[i].equals("-"))
			{
			while(!stack.empty()&&(stack.peek().equals("*")||stack.peek().equals("/") ||stack.peek().equals("+")||stack.peek().equals("-"))) {
				sb[i]=stack.pop();
			}
			stack.push(line[i]);
		}else if(line[i].equals("(")){
			stack.push(line[i]);
		}else if(line[i].equals(")")){
			while(!stack.peek().equals("(")){
				sb[i]=stack.pop();
			}
			stack.pop();
		}
		else
			sb[i]=line[i];
		}
		while(!stack.empty()){
			sb[i]=stack.pop();
			i++;
		}

		for(int j=0;j<sb.length;j++){
			if(sb[j]==null){ }
			else send.append(sb[j]+" ");
		}
		
		int answer = calPostfix(send.toString());

		return answer;
	}

	public int calPostfix(String input) {
 
        Stack<Integer> stack = new Stack<>();
		String[] line = input.split(" ");
        int len = line.length;

		System.out.println(input);
 
       for (int i = 0; i < len; i++) {
            String op = line[i];

           if (op.equals("+") || op.equals("-")|| op.equals("*")|| op.equals("/"))
			   {
                int op2 = stack.pop();
                int op1 = stack.pop();
 
                switch (op) {
 
                case "+":
                    stack.push(op1 + op2);
                    break;
                case "-":
                    stack.push(op1 - op2);
                    break;
                case "*":
                    stack.push(op1 * op2);
                    break;
                case "/":
                    stack.push(op1 / op2);
                    break;
                }
            }					
			
			else{
					stack.push(Integer.parseInt(op));}
        }
        return stack.pop();
    }
}

import java.util.Comparator;
import java.util.PriorityQueue;

public class StreamMedian {
	
	private int count = 0;
	// ���ȶ��м���ʵ���˶ѣ�Ĭ��ʵ�ֵ�С����
	private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
	// �������ѣ����ıȽϷ�ʽ
	private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(15, new Comparator<Integer>() {
		public int compare(Integer i1, Integer i2) {
			return i2 - i1;
		}
	});
	
	public void insert(Integer num) {
		if ((count & 1) != 0) {
            // ����������Ϊ����ʱ���¼����Ԫ�أ�Ӧ������С����
            // ��ע�ⲻ��ֱ�ӽ���С���ѣ����Ǿ������ɸѡ��ȡ����������Ԫ�ؽ���С���ѣ�
            // 1.�¼����Ԫ�����뵽����ѣ��ɴ����ɸѡ����������Ԫ��
			maxHeap.offer(num);
			int filteredMaxNum = maxHeap.poll();
			minHeap.offer(filteredMaxNum);
		} else {
            // ����������Ϊż��ʱ���¼����Ԫ�أ�Ӧ����������
            // ��ע�ⲻ��ֱ�ӽ������ѣ����Ǿ�С����ɸѡ��ȡС���������Ԫ�ؽ������ѣ�
            // 1.�¼����Ԫ�����뵽С���ѣ���С����ɸѡ��������С��Ԫ��
			minHeap.offer(num);
			int filteredMinNum = minHeap.poll();
			maxHeap.offer(filteredMinNum);
		}
		count++;
	}
	
	public double getMedian() {
		if ((count & 1) == 0)
			return (new Double(minHeap.peek() + maxHeap.peek())) / 2;
		else 
			return maxHeap.peek();
	}
	
	public static void main(String[] args) {
		StreamMedian s = new StreamMedian();
		s.insert(6);//
		s.insert(9);//
		s.insert(3);//
		s.insert(0);//
		s.insert(11);//
		s.insert(345);//
		s.insert(78);//
		s.insert(2);//
//		s.insert(36);//
		
		System.out.println(s.getMedian());
	
		
	}

}

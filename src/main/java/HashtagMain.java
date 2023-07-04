public class HashtagMain {
	public static void main(String[] args) {
		String content = "안녕하세요 #구디아카데미 입니다. 하반기 #자바 교육과정 시간표 공지하였습니다";
		
		String[] list = content.split(" ");
		
		/*
		for(String s : list) {
			if(s.lastIndexOf("#") != -1) {
				String[] list2 = s.split("#");
				
				System.out.println(s.substring(s.lastIndexOf("#")+1));
			}
		}
		*/
		int count = 0;
		for (String hashtag : list) {
			if (hashtag.startsWith("#")) {
				String h = hashtag.replace("#", "");
				if (h.length() > 0) {
					count++;
					System.out.println(h);
				}
			}
		}
		System.out.println("해시태그개수 : " + count);

		/*
		 [출력]
		 구디아카데미
		 자바
		 */
	}
}

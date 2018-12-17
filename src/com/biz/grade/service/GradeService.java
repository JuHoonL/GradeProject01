package com.biz.grade.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.biz.grade.contact.GradeContact;
import com.biz.grade.vo.GradeVO;

public class GradeService {

	String stdFile;
	String scoreFile;
	String gradeFile;
	
	List<GradeVO> gradeList;
	List<GradeVO> scoreList;
	
	public GradeService(String[] Files) {
		gradeList = new ArrayList();
		scoreList = new ArrayList();
		stdFile = Files[GradeContact.FILES_STD];
		scoreFile = Files[GradeContact.FILES_SCORE];
		gradeFile = Files[GradeContact.FILES_GRADE];
	}
	
	public void stdFileRead() {
		FileReader fr;
		BufferedReader br;
		
		try {
			fr = new FileReader(stdFile);
			br = new BufferedReader(fr);
			while(true) {
				String reader = br.readLine();
				if(reader == null) break;
				
				String[] stds = reader.split(":");
				
				GradeVO vo = new GradeVO();
				vo.setStrNum(stds[GradeContact.ST_strNum]);
				vo.setStrName(stds[GradeContact.ST_strName]);
				vo.setStrTel(stds[GradeContact.ST_strTel]);
				vo.setStrAddr(stds[GradeContact.ST_strAddr]);
				
				gradeList.add(vo);
				
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void scoreFileRead() {
		FileReader fr;
		BufferedReader br;
		
		try {
			fr = new FileReader(scoreFile);
			br = new BufferedReader(fr);
			while(true) {
				String reader = br.readLine();
				if(reader == null) break;
				
				String[] scores = reader.split(":");
				
				String strNum = scores[0];
				
				for(GradeVO vo : gradeList) {
					if(vo.getStrNum().equals(strNum)) {
						int Kor = Integer.valueOf(scores[1]);
						int Eng = Integer.valueOf(scores[2]);
						int Mth = Integer.valueOf(scores[3]);
						vo.setIntKor(Kor);
						vo.setIntEng(Eng);
						vo.setIntMth(Mth);
						vo.setIntSum(Kor + Eng + Mth);
						vo.setFloatAvg((Kor + Eng + Mth) / 3.0f);
					}
				}
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void scoreFileRead01() {
		FileReader fr;
		BufferedReader br;
		
		try {
			fr = new FileReader(scoreFile);
			br = new BufferedReader(fr);
			while(true) {
				String reader = br.readLine();
				if(reader == null) break;
				
				String[] scores = reader.split(":");
				GradeVO vo = new GradeVO();
				int Kor = Integer.valueOf(scores[GradeContact.SC_intKor]);
				int Eng = Integer.valueOf(scores[GradeContact.SC_intEng]);
				int Mth = Integer.valueOf(scores[GradeContact.SC_intMth]);
				vo.setStrNum(scores[GradeContact.SC_strNum]);
				vo.setIntKor(Kor);
				vo.setIntEng(Eng);
				vo.setIntMth(Mth);
				vo.setIntSum(Kor + Eng + Mth);
				vo.setFloatAvg((Kor + Eng + Mth) / 3.0f);
				scoreList.add(vo);
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void stdMatchScore() {
		for(GradeVO vg : gradeList) {
			for(GradeVO vs : scoreList) {
				if(vg.getStrNum().equals(vs.getStrNum())){
					vg.setIntKor(vs.getIntKor());
					vg.setIntEng(vs.getIntEng());
					vg.setIntMth(vs.getIntMth());
					vg.setIntSum(vs.getIntSum());
					vg.setFloatAvg(vs.getFloatAvg());
				}
			}
		}
	}
	
	
	public void viewScore() {
		System.out.println("========================================================================================================");
		System.out.println("학번\t이름\t전화번호\t\t\t\t주소\t\t\t국어\t영어\t수학\t총점\t평균");
		System.out.println("========================================================================================================");
		
		for(GradeVO vo : gradeList) {
			System.out.printf("%3s%8.8s%15.15s%20.20s\t\t\t\t%3d\t%3d\t%3d\t%3d\t%3.2f\n",
					vo.getStrNum(),
					vo.getStrName(),
					vo.getStrTel(),
					vo.getStrAddr(),
					vo.getIntKor(),
					vo.getIntEng(),
					vo.getIntMth(),
					vo.getIntSum(),
					vo.getFloatAvg());
		}
		System.out.println("=========================================================================================================");
	}
	
	public void writeScore() {
		PrintWriter pw;
		
		try {
			pw = new PrintWriter(gradeFile);
			
			for(GradeVO vo : gradeList) {
				pw.printf("%s:%s:%s:%s:%d:%d:%d:%d:%f\n",
						vo.getStrNum(),
						vo.getStrName(),
						vo.getStrTel(),
						vo.getStrAddr(),
						vo.getIntKor(),
						vo.getIntEng(),
						vo.getIntMth(),
						vo.getIntSum(),
						vo.getFloatAvg());
			}
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

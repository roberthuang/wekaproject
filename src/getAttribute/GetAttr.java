package getAttribute;

import java.io.*;
import java.util.*;

public class GetAttr {
	private static HashMap<Integer, Double> temp_sl = new HashMap<>();
	private static HashMap<Integer, Double> temp_ll = new HashMap<>();
	
	public static HashMap<Integer, String> FS_oil(int att_index, ArrayList<ArrayList<String>> records) {
		 HashMap<Integer, String> result = new HashMap<>(); 	    
	     int col = att_index; 
	     int rise_number = 0;
	     int down_number = 0;
	     for (int i = 1; i < records.size(); i++ ) {       
	            if (i == 1) {	                
	                continue;
	            }
	            
	            if (Double.parseDouble(records.get(i).get(col))- Double.parseDouble(records.get(i-1).get(col)) > 0 ) {
	    	    	result.put(i, "R");     
	    	    	rise_number++;
	    	    } else {
	    	    	result.put(i, "D");  
	    	    	down_number++;
	    	    }	
        } 
	    for (int i = 1; i < records.size(); i++) {
	    	if (result.get(i) == null) {
	    		if (rise_number > down_number) {
	    			result.put(i, "R");	    			
	    		} else {
	    			result.put(i, "D");	    
	    		}
	    	}
	    }
	     
	    return result;		
	}
	public static HashMap<Integer, String> feature(int att_index, ArrayList<ArrayList<String>> records) {
		 HashMap<Integer, String> result = new HashMap<>(); 	    
	     int col = att_index; 
	     int rise_number = 0;
	     int down_number = 0;
	     for (int i = 1; i < records.size(); i++ ) {       
	            if (i == 1) {	                
	                continue;
	            }
	            
	            if (Double.parseDouble(records.get(i).get(col))- Double.parseDouble(records.get(i-1).get(col)) > 0 ) {
	    	    	result.put(i, "R");     
	    	    	rise_number++;
	    	    } else {
	    	    	result.put(i, "D");  
	    	    	down_number++;
	    	    }	
       } 
	    for (int i = 1; i < records.size(); i++) {
	    	if (result.get(i) == null) {
	    		if (rise_number > down_number) {
	    			result.put(i, "R");	    			
	    		} else {
	    			result.put(i, "D");	    
	    		}
	    	}
	    }
	     
	    return result;		
	}
	public static HashMap<Integer, String> feature_categories(int att_index, ArrayList<ArrayList<String>> records, int rise, int down) {
		 HashMap<Integer, String> result = new HashMap<>(); 	    
	     int col = att_index; 
	     for (int i = 1; i < records.size(); i++ ) {       
	            if (i == 1) {
	                result.put(i, Integer.toString(rise));     
	                continue;
	            }
	            
	            if (Double.parseDouble(records.get(i).get(col))- Double.parseDouble(records.get(i-1).get(col)) > 0 ) {
	    	    	result.put(i, Integer.toString(rise));     
	    	    } else {
	    	    	result.put(i, Integer.toString(down));  
	    	    }	
        }       	        	
	    return result;		
	}
	
	
	
	public static HashMap<Integer, Double> feature2_weka(int att_index, ArrayList<ArrayList<String>> records) {
		 HashMap<Integer, Double> result = new HashMap<>(); 	    
	     int col = att_index; 
	     for (int i = 1; i < records.size(); i++ ) {       
	            if (i == 1) {
	            	
	            } else {	            
	                double min = Double.parseDouble(records.get(i).get(col))- Double.parseDouble(records.get(i-1).get(col));
	                result.put(i, min);
	            }
         }    
	     
	     double average =  0.0;
	    	for (int i = 1; i < result.size(); i++) {
	    		if (result.get(i) != null) {
	    			average += result.get(i);
	    		}
	    	}
	    	
	    	average /= (double) result.size();
	    	for (int i = 1; i < result.size(); i++) {
	    		if (result.get(i)== null) {
	    			result.put(i, average);
	    		}
	    	}
	     return result;		
	}
	
	public static HashMap<Integer, String> feature2(int att_index, ArrayList<ArrayList<String>> records) {
		 HashMap<Integer, String> result = new HashMap<>(); 	    
	     int col = att_index; 
	     for (int i = 1; i < records.size(); i++ ) {       
	            if (i == 1) {
	            	result.put(i, records.get(0).get(col) + "_R");      
	                continue;
	            }
	            
	            if (Double.parseDouble(records.get(i).get(col))- Double.parseDouble(records.get(i-1).get(col)) >= 0 ) {
	    	    	result.put(i,  records.get(0).get(col) + "_R");     
	    	    } else {
	    	    	result.put(i,  records.get(0).get(col) + "_D");  
	    	    }	
       }       	        	
	    return result;		
	}
	
	
	public static HashMap<Integer, String> match_source_target(HashMap<Integer, String> s, HashMap<Integer, String> t, int sou, int tar) {
		HashMap<Integer, String> result = new HashMap<>(); 
	    for (int i = 1;i <= t.size(); i++) {
	        if (s.get(i) == t.get(i)) {
	        	result.put(i, "Same" + "_" + sou + "_" + tar);
	        } else {
	        	result.put(i, "Diff" + "_" + sou + "_" + tar);
	        }
	    }	    
	    return result;
	}
	
	
	public static HashMap<Integer, String> match_source_target_categories(HashMap<Integer, String> s, HashMap<Integer, String> t, int rise, int down) {
		HashMap<Integer, String> result = new HashMap<>(); 
	    for (int i = 1;i <= t.size(); i++) {
	        if (s.get(i) == t.get(i)) {
	        	result.put(i, Integer.toString(rise));
	        } else {
	        	result.put(i, Integer.toString(down));
	        }
	    }	    
	    return result;
	}
	
	public static HashMap<Integer, String> match_source_target_technical(HashMap<Integer, String> s, HashMap<Integer, String> t, int sou, int tar, String str) {
		HashMap<Integer, String> result = new HashMap<>(); 
	    for (int i = 1;i <= t.size(); i++) {
	    	char s1 = s.get(i).charAt(s.get(i).length()-1);
	    	char t1 = t.get(i).charAt(t.get(i).length()-1);
	        if (s1 == t1) {
	        	result.put(i, "Same" + "_" + sou + "_" + tar + "_" + str);
	        } else {
	        	result.put(i, "Diff" + "_" + sou + "_" + tar + "_" + str);
	        }
	    }	    
	    return result;
	}
	
	
	public static HashMap<Integer, String> Move_Average_same(int length1, int length2, String att, int att_index, ArrayList<ArrayList<String>> records) {
		HashMap<Integer, String> result = new HashMap<>();
		int col = att_index;   
		for (int i = 1; i < records.size(); i++ ) {       
	           if (i <= length1) {
	               result.put(i, "MAa"+ att.charAt(0) + length1 + "_1");     
	               continue;
	           }
	           double sum_t1 = 0;
	           double sum_t2= 0;
	           if (i - length1 + 1 >= 1) { 
	        	   for (int p_1 = i; p_1 >= i-length1+1; p_1--) {                
	                    sum_t1 = sum_t1+ Double.parseDouble(records.get(p_1).get(col));
	        	   }
	           }
	           if (i - length2 + 1 >= 1) { 
	        	   for (int p_1 = i; p_1 >= i-length2+1; p_1--) {                
	                    sum_t2 = sum_t2 + Double.parseDouble(records.get(p_1).get(col));
	        	   }
	           }
	           double MA = sum_t1/length1 - sum_t2/length2;
	           if (MA >= 0) {	                
	                result.put(i, "MAa" + att.charAt(0) + length1 + "_1");    
	           } else {	                
	                result.put(i, "MAa" + att.charAt(0) + length1 + "_0"); 
	           }       	           
		}
		return result;
	}
	public static HashMap<Integer, Double> Move_Average_weka(int length, String att, int att_index, ArrayList<ArrayList<String>> records) {
        HashMap<Integer, Double> result = new HashMap<>();    
        //The column of Target
        int col = att_index;                                                                                                                            
        for (int i = 1; i < records.size(); i++ ) {       
            if (i <= length) {
              
            }
            
            double sum_t = 0;
            double sum_t_1 = 0;
            if (i - length + 1 >= 1) {         
                for (int p_1 = i; p_1 >= i-length+1; p_1--) {                
                    sum_t = sum_t + Double.parseDouble(records.get(p_1).get(col));
                } 
                     
                int j = i - 1;
                if (j - length + 1 >=1) {
                    
                    for (int p_2 = j; p_2 >= j-length+1; p_2--) {
                       
                        sum_t_1 = sum_t_1 + Double.parseDouble(records.get(p_2).get(col));
                    }
                }
            }          
            
            double MA = sum_t/length - sum_t_1/length;     
            result.put(i, MA);
        }  
        
        double average =  0.0;
    	for (int i = 1; i < result.size(); i++) {
    		if (result.get(i) != null) {
    			average += result.get(i);
    		}
    	}
    	
    	average /= (double) result.size();
    	for (int i = 1; i < result.size(); i++) {
    		if (result.get(i) == null) {
    			result.put(i, average);
    		}
    	}
        return result;
    }    
	
    public static HashMap<Integer, String> Move_Average(int length, String att, int att_index, ArrayList<ArrayList<String>> records) {
        HashMap<Integer, String> result = new HashMap<>();    
        //The column of Target
        int col = att_index;                                                                                                                            
        for (int i = 1; i < records.size(); i++ ) {       
            if (i <= length) {
                result.put(i, "MA"+ att.charAt(0) + length + "_1");     
                continue;
            }
            
            double sum_t = 0;
            double sum_t_1 = 0;
            if (i -length + 1 >= 1) {         
                for (int p_1 = i; p_1 >= i-length+1; p_1--) {                
                    sum_t = sum_t + Double.parseDouble(records.get(p_1).get(col));
                } 
                     
                int j = i - 1;
                if (j - length + 1 >=1) {
                    
                    for (int p_2 = j; p_2 >= j-length+1; p_2--) {
                       
                        sum_t_1 = sum_t_1 + Double.parseDouble(records.get(p_2).get(col));
                    }
                }
            }          
            
            //Rise or Down
            double MA = sum_t/length - sum_t_1/length;     
            if (MA >= 0) {
                //System.out.println("i: " + i + " " + MA);
                result.put(i, "MA" + att.charAt(0) + length + "_1");    
            } else {
                //System.out.println("i: " + i + " " + MA);
                result.put(i, "MA" + att.charAt(0) + length + "_0"); 
            }              
        }            
        return result;
    }    
	 
    public static HashMap<Integer, String> BIAS(int length, int att_index, double threshold, ArrayList<ArrayList<String>> records) {
    	HashMap<Integer, String> result = new HashMap<>();
    	int col = att_index;   
    	int rise_number = 0;
    	int down_number = 0;
    	for (int i = 1; i < records.size(); i++) {
    		double bias;
    	    if (i <= length-1) {
    	    	//result.put(i, "BIAS_" + records.get(0).get(att_index).charAt(0) + "_" + length + "_" + threshold + "_1");
    	    } else {
    	    	double sum_t = 0;
    	    	if (i - length + 1 >= 1) {
    	    		for (int j = i; j >= i-length+1; j--) {                
                        sum_t = sum_t + Double.parseDouble(records.get(j).get(col));
                    } 	    	    		
    	    	}
    	    	sum_t = sum_t / (double)length;
    	    	bias = (Double.parseDouble(records.get(i).get(att_index)) - sum_t)/(double) sum_t;
    	    	if (bias >= threshold) {
    	    		result.put(i, "BIAS_" + records.get(0).get(att_index).charAt(0) + "_" + length + "_" + threshold + "_1");	
    	    		 rise_number++;
    	    	} else {
    	    		result.put(i, "BIAS_" + records.get(0).get(att_index).charAt(0) + "_" + length + "_" + threshold + "_0");	
    	    		down_number++;
    	    	}    	    	
    	    }
    		
    	}
    	for (int i = 1; i < records.size(); i++) {
    		if (result.get(i) == null) {
    			if (rise_number > down_number) {
    				result.put(i, "BIAS_" + records.get(0).get(att_index).charAt(0) + "_" + length + "_" + threshold + "_1");
    			} else {
    				result.put(i, "BIAS_" + records.get(0).get(att_index).charAt(0) + "_" + length + "_" + threshold + "_0");
    			}
    		}
    	}
    	
    	
    	return result;
    }
    
    public static HashMap<Integer, String> BIAS_categories(int length, int att_index, double threshold, ArrayList<ArrayList<String>> records, int rise, int down) {
    	HashMap<Integer, String> result = new HashMap<>();
    	int col = att_index;   
    	int rise_number = 0;
    	int down_number = 0;
    	for (int i = 1; i < records.size(); i++) {
    		double bias;
    	    if (i <= length-1) {
    	    	//result.put(i, "BIAS_" + records.get(0).get(att_index).charAt(0) + "_" + length + "_" + threshold + "_1");
    	    } else {
    	    	double sum_t = 0;
    	    	if (i - length + 1 >= 1) {
    	    		for (int j = i; j >= i-length+1; j--) {                
                        sum_t = sum_t + Double.parseDouble(records.get(j).get(col));
                    } 	    	    		
    	    	}
    	    	sum_t = sum_t / (double)length;
    	    	bias = (Double.parseDouble(records.get(i).get(att_index)) - sum_t)/(double) sum_t;
    	    	if (bias >= threshold) {
    	    		result.put(i, Integer.toString(i));	
    	    		 rise_number++;
    	    	} else {
    	    		result.put(i, "BIAS_" + records.get(0).get(att_index).charAt(0) + "_" + length + "_" + threshold + "_0");	
    	    		down_number++;
    	    	}    	    	
    	    }
    		
    	}
    	for (int i = 1; i < records.size(); i++) {
    		if (result.get(i) == null) {
    			if (rise_number > down_number) {
    				result.put(i, Integer.toString(rise));
    			} else {
    				result.put(i, Integer.toString(down));
    			}
    		}
    	}
    	
    	
    	return result;
    }
    
    public static HashMap<Integer, Double> BIAS_weka(int length, int att_index, double threshold, ArrayList<ArrayList<String>> records) {
    	HashMap<Integer, Double> result = new HashMap<>();
    	int col = att_index;   
    	for (int i = 1; i < records.size(); i++) {
    		double bias;
    	    if (i <= length-1) {
    	    	
    	    } else {
    	    	double sum_t = 0;
    	    	if (i - length + 1 >= 1) {
    	    		for (int j = i; j >= i-length+1; j--) {                
                        sum_t = sum_t + Double.parseDouble(records.get(j).get(col));
                    } 	    	    		
    	    	}
    	    	sum_t = sum_t / (double)length;
    	    	bias = (Double.parseDouble(records.get(i).get(att_index)) - sum_t)/(double) sum_t;
    	    	result.put(i, bias);  	    	
    	    }
    		
    	}
    	
    	double average =  0.0;
    	for (int i = 1; i < result.size(); i++) {
    		if (result.get(i) != null) {
    			average += result.get(i);
    		}
    	}
    	
    	average /= (double) result.size();
    	for (int i = 1; i < result.size(); i++) {
    		if (result.get(i) == null) {
    			result.put(i, average);
    		}
    	}
    	return result;
    }
    
    
	public static void featureExtraction(String output_filename, ArrayList<ArrayList<String>> records, int periods, double threshold) {						
		ArrayList<ArrayList<String>> result = new ArrayList<>();
		HashMap<Integer, String> FS_rate = feature(3, records);
		
		HashMap<Integer, String> FS_rubber = feature(2, records);		
		HashMap<Integer, String> FS_oil = feature(1, records);
		HashMap<Integer, String> FT_but = feature(4, records);
		//0, 1
		HashMap<Integer, String> FT_but_catecories = feature_categories(4, records, 0,1);
	
		
		HashMap<Integer, String> Match_of_oil_rate = match_source_target(FS_oil, FS_rate, 1, 3);
		HashMap<Integer, String> Match_of_rubber_but = match_source_target(FS_rubber, FT_but, 2, 4);
		//2, 3
		HashMap<Integer, String> Match_of_rubber_but_categories = match_source_target_categories(FS_rubber, FT_but, 2, 4);
		
		HashMap<Integer, String> Match_of_oil_but = match_source_target(FS_oil, FT_but, 1, 4);
		
		HashMap<Integer, String> MA_rubber_3 = Move_Average(3, records.get(0).get(2), 2, records);
		//HashMap<Integer, String> MA_but = Move_Average(period, records.get(0).get(4), 4, records);
//		HashMap<Integer, String> Match_of_MA3_rubber_but = match_source_target_technical(MA_rubber_3, MA_but_3, 2, 4, "MA3");
		
//		HashMap<Integer, String> MACD_rubber_1_2_3 = MACD(1, 2, 3,records.get(0).get(2), records);
//		HashMap<Integer, String> MACD_T_1_2_3 = MACD(1, 2, 3,records.get(0).get(4), records);
//		HashMap<Integer, String> MACD_T = MACD(m1, m2, m3,records.get(0).get(4), records);
		
//		HashMap<Integer, String> MA_T_2 = Move_Average(2, records.get(0).get(4), 4, records);
		
		//HashMap<Integer, String> BIAS_rate_2_03 = BIAS(2, 3, 0.0003, records);
		HashMap<Integer, String> BIAS_T_2_03 = BIAS(periods, 4, threshold, records);
		
		for (int i = 0; i < records.size(); i++) {		
			ArrayList<String> temp = new ArrayList<>();
			//Add Date
			temp.add(records.get(i).get(0));
			if(i == 0) {			
//				temp.add("FT_but");
//				temp.add("BIAS_T_2_03");
				//temp.add("BIAS_T_2_03");

//				temp.add("Match_of_rubber_but_categories");
//			    temp.add("Match_of_rubber_but");
			    temp.add("Match_of_oil_rate");
//			    temp.add("Match_of_oil_but");
//			    temp.add("MA_but_2");
//			    temp.add("BIAS_T_2_03");	
//			    temp.add("Match_of_MA3_rubber_but");	
			} else {
//				temp.add(FT_but.get(i));
//				temp.add(BIAS_T_2_03.get(i));
				//All the conditional att need to add. eg. x -> x x_3 x_4		
				//temp.add(BIAS_T_2_03.get(i));
//			    temp.add(FT_but_catecories.get(i));	
//			    temp.add(Match_of_rubber_but_categories.get(i));
//		        temp.add(Match_of_rubber_but.get(i));
		        temp.add(Match_of_oil_rate.get(i));
//	            temp.add(Match_of_oil_but.get(i));
//		        temp.add(MA_but_2.get(i));
//		        temp.add(BIAS_T_2_03.get(i));
//		        temp.add(Match_of_MA3_rubber_but.get(i));
			}
			//Add the last one of every line
			temp.add(records.get(i).get(records.get(i).size()-1));
			
			result.add(temp);
		}		
		try {
		writeCSV("", output_filename,result);
		} catch (IOException e) {
			System.out.println("[ERROR] I/O Exception.");
			e.printStackTrace();
		}
	}
	
	public static void featureExtraction_episode(String output_filename, ArrayList<ArrayList<String>> records, HashMap<Integer, String> class_table) {						
		ArrayList<ArrayList<String>> result = new ArrayList<>();
		HashMap<Integer, String> FS_rate = feature(3, records);		
		HashMap<Integer, String> FS_rubber = feature(2, records);		
		HashMap<Integer, String> FS_oil = feature(1, records);
		HashMap<Integer, String> FT_but = feature(4, records);
		
		HashMap<Integer, String> Match_of_oil_rate = match_source_target(FS_oil, FS_rate, 1, 3);
		HashMap<Integer, String> Match_of_rubber_but = match_source_target(FS_rubber, FT_but, 2, 4);
		HashMap<Integer, String> Match_of_oil_but = match_source_target(FS_oil, FT_but, 1, 4);
		
		HashMap<Integer, String> MA_rubber_3 = Move_Average(3, records.get(0).get(2), 2, records);
		HashMap<Integer, String> MA_but_2 = Move_Average(2, records.get(0).get(4), 4, records);
//		HashMap<Integer, String> Match_of_MA3_rubber_but = match_source_target_technical(MA_rubber_3, MA_but_3, 2, 4, "MA3");
		
//		HashMap<Integer, String> MACD_rubber_1_2_3 = MACD(1, 2, 3,records.get(0).get(2), records);
//		HashMap<Integer, String> MACD_T_1_2_3 = MACD(1, 2, 3,records.get(0).get(4), records);
//		HashMap<Integer, String> MACD_T_2_3_4 = MACD(2, 3, 4,records.get(0).get(4), records);
		
//		HashMap<Integer, String> MA_T_2 = Move_Average(2, records.get(0).get(4), 4, records);
		
		//HashMap<Integer, String> BIAS_rate_2_03 = BIAS(2, 3, 0.0003, records);
		HashMap<Integer, String> BIAS_T_2_03 = BIAS(2, 4, 0.0003, records);
		
		for (int i = 0; i < records.size(); i++) {		
			ArrayList<String> temp = new ArrayList<>();
			//Add Date
			temp.add(records.get(i).get(0));
			if(i == 0) {			 			     
				temp.add("FT_but");
			    temp.add("Match_of_rubber_but");
			    
//			    temp.add("Match_of_oil_rate");
//			    temp.add("Match_of_oil_but");
//			    temp.add("MA_but_2");
//			    temp.add("BIAS_T_2_03");	
//			    temp.add("Match_of_MA3_rubber_but");
			    temp.add("Target");
			} else {
				//All the conditional att need to add. eg. x -> x x_3 x_4		  
				temp.add(FT_but.get(i));
		        temp.add(Match_of_rubber_but.get(i));
//		        temp.add(Match_of_oil_rate.get(i));
//		        temp.add(Match_of_oil_but.get(i));
//		        temp.add(MA_but_2.get(i));
//		        temp.add(BIAS_T_2_03.get(i));
//		        temp.add(Match_of_MA3_rubber_but.get(i));
		        temp.add(class_table.get(i));
			}
			//Add the last one of every line
			//temp.add(records.get(i).get(records.get(i).size()-1));
			
			result.add(temp);
		}		
		try {
		writeCSV("", output_filename,result);
		} catch (IOException e) {
			System.out.println("[ERROR] I/O Exception.");
			e.printStackTrace();
		}
	}
	
	
public static void featureExtraction2(String output_filename, ArrayList<ArrayList<String>> records, HashMap<Integer, String> target, int window_size) {				
		
		ArrayList<ArrayList<String>> result = new ArrayList<>();
		HashMap<Integer, String> F_oil = feature2(2, records);	    
	    HashMap<Integer, String> F_but = feature2(4, records);
			
		HashMap<Integer, String> FS_oil = feature(2, records);		
		HashMap<Integer, String> FS_cru = feature(1, records);
		HashMap<Integer, String> FT_but = feature(4, records);	
			
		HashMap<Integer, String> Match_of_oil_but = match_source_target(FS_oil, FT_but, 2, 4);
		HashMap<Integer, String> Match_of_cru_but = match_source_target(FS_cru, FT_but, 1, 4);
			
		HashMap<Integer, String> MACD_oil_1_2_3 = MACD(1, 2, 3,records.get(0).get(2), records);
		//HashMap<Integer, String> MACD_rate_1_2_3 = MACD(1, 2, 3,records.get(0).get(3), records);
		HashMap<Integer, String> MACD_T_1_2_3 = MACD(1, 2, 3,records.get(0).get(4), records);
		HashMap<Integer, String> MACD_T_2_3_4 = MACD(2, 3, 4,records.get(0).get(4), records);
			
		//HashMap<Integer, String> BIAS_rate_2_03 = BIAS(2, 3, 0.0003, records);
		HashMap<Integer, String> BIAS_T_2_03 = BIAS(2, 4, 0.0003, records);
		
		for (int i = 0; i <= records.size()*0.8; i++) {		
			ArrayList<String> temp = new ArrayList<>();
			//Add Date
			temp.add(records.get(i).get(0));
			if(i == 0) {			 			       
			    temp.add("F_oil");			 
			    temp.add("F_but");
			    temp.add("Match_of_oil_but");	
			    temp.add("Match_of_cru_but");	
			    temp.add("MACD_oil_1_2_3");
			    //temp.add("MACD_rate_1_2_3");
			    temp.add("MACD_T_1_2_3");
			    temp.add("MACD_T_2_3_4");
			    //temp.add("BIAS_rate_2_03");
               temp.add("BIAS_T_2_03");			
			    temp.add(records.get(i).get(records.get(i).size()-1));	  
			} else {
				//All the conditional att need to add. eg. x -> x x_3 x_4		       		        
		        temp.add(F_oil.get(i));		           
		        temp.add(F_but.get(i));
		        temp.add(Match_of_oil_but.get(i));
		        temp.add(Match_of_cru_but.get(i));
		        temp.add(MACD_oil_1_2_3.get(i));
		        //temp.add(MACD_rate_1_2_3.get(i));
		        temp.add(MACD_T_1_2_3.get(i));
		        temp.add(MACD_T_2_3_4.get(i));
		        //temp.add(BIAS_rate_2_03.get(i));
		        temp.add(BIAS_T_2_03.get(i));		       
		        temp.add(target.get(i));			 
			}	
			result.add(temp);
		}		
		try {
		writeCSV("", output_filename,result);
		} catch (IOException e) {
			System.out.println("[ERROR] I/O Exception.");
			e.printStackTrace();
		}
	}
	 /*
	 public static void featureExtraction_one(String output_filename, ArrayList<ArrayList<String>> records) {				
			
			ArrayList<ArrayList<String>> result = new ArrayList<>();
						
			for (int i = 0; i < records.size(); i++) {		
				ArrayList<String> temp = new ArrayList<>();
				//Add Date
				temp.add(records.get(i).get(0));
				if(i == 0) {			 
				       //temp.add(records.get(i).get(1));
				       temp.add("Feature_S");				      			    
				} else {
					   if (Double.parseDouble(records.get(i).get(1)) <= 77.59) {
					       temp.add("RR");   
					   } else {
						   temp.add("DD"); 
					   }			       
			        	//temp.add(records.get(i).get(1));			          			        	     		    
				}
				//Add the last one of every line
				temp.add(records.get(i).get(records.get(i).size()-1));			
				result.add(temp);
			}		
			try {
			writeCSV("", output_filename,result);
			} catch (IOException e) {
				System.out.println("[ERROR] I/O Exception.");
				e.printStackTrace();
			}
		} 
	 */	
    //weka
    public static void featureExtraction_weka(String output_filename, ArrayList<ArrayList<String>> records, HashMap<Integer, String> feature_target, List<String> para_list) {		
    	
		ArrayList<ArrayList<String>> result = new ArrayList<>();
		HashMap<Integer, String> FS_rate = feature(3, records);		
		HashMap<Integer, String> FS_rubber = feature(2, records);		
		HashMap<Integer, String> FS_oil = feature(1, records);
		HashMap<Integer, String> FT_but = feature(4, records);
		//0,1
		HashMap<Integer, String> FT_but_categories = feature_categories(4, records,0,1);
		
		HashMap<Integer, Double> FT_but_value = feature2_weka(4, records);
	    
		HashMap<Integer, Double> MA_T_2 = Move_Average_weka(2, records.get(0).get(4), 4, records);
		
		//2,3
		HashMap<Integer, String> Match_of_oil_rate_categories = match_source_target_categories(FS_oil, FS_rate,2,3);
		//4,5
		HashMap<Integer, String> Match_of_rate_but_categories = match_source_target_categories(FS_rate, FT_but,4,5);
		//6,7
		HashMap<Integer, String> Match_of_oil_but_categories = match_source_target_categories(FS_oil, FT_but, 6,7);
		//8,9
		HashMap<Integer, String> Match_of_rubber_but_categories = match_source_target_categories(FS_rubber, FT_but, 8,9);
		
		//10,11
		HashMap<Integer, String> FS_oil_categories = feature_categories(1, records,10,11);
		//12,13
		HashMap<Integer, String> FS_rubber_categories = feature_categories(2, records,12,13);
		//14,15
		HashMap<Integer, String> FS_rate_categories = feature_categories(3, records,14,15);
		
		//16,17
		HashMap<Integer, String> BIAS_T_2_0001 = BIAS_categories(2, 4, 0.0001, records, 16,17);
		//18,19				
		HashMap<Integer, String> BIAS_T_2_001 = BIAS_categories(2, 4, 0.001, records, 18,19);
		//20,21				
	    HashMap<Integer, String> BIAS_T_2_01 = BIAS_categories(2, 4, 0.01, records, 20,21);		
	    
	    //22,23
	  	HashMap<Integer, String> BIAS_T_3_0001 = BIAS_categories(3, 4, 0.0001, records, 22,23);
	  	//24,25				
	  	HashMap<Integer, String> BIAS_T_3_001 = BIAS_categories(3, 4, 0.001, records, 24,25);
	  	//26,27			
	  	HashMap<Integer, String> BIAS_T_3_01 = BIAS_categories(3, 4, 0.01, records, 26,27);		
	    
	    //28,29
	  	HashMap<Integer, String> BIAS_T_4_0001 = BIAS_categories(4, 4, 0.0001, records, 28,29);
	  	//30,31		
	  	HashMap<Integer, String> BIAS_T_4_001 = BIAS_categories(4, 4, 0.001, records, 30,31);
	  	//32,33			
	  	HashMap<Integer, String> BIAS_T_4_01 = BIAS_categories(4, 4, 0.01, records, 32,33);		
	    
	    //34,35
	  	HashMap<Integer, String> BIAS_T_5_0001 = BIAS_categories(5, 4, 0.0001, records, 34,35);
	  	//36,37			
	  	HashMap<Integer, String> BIAS_T_5_001 = BIAS_categories(5, 4, 0.001, records, 36,37);
	  	//38,39			
	  	HashMap<Integer, String> BIAS_T_5_01 = BIAS_categories(5, 4, 0.01, records, 38,39);		
	    
	  	
	    
		for (int i = 0; i < records.size(); i++) {		
			ArrayList<String> temp = new ArrayList<>();
			//Add time
			temp.add(records.get(i).get(0));
			if(i == 0) {
				for (int j = 0; j < para_list.size(); j++) {
					temp.add(para_list.get(j));
				}				
				temp.add("Target");
			} else {
				for (int j = 0; j < para_list.size(); j++) {
					switch(para_list.get(j)) {
					    case "Match_of_oil_rate_categories":
					    	temp.add(Match_of_oil_rate_categories.get(i));
					    	break;
					    case "Match_of_rate_but_categories":
					    	temp.add(Match_of_rate_but_categories.get(i));
					    	break;
					    case "Match_of_oil_but_categories":
					    	temp.add(Match_of_oil_but_categories.get(i));
					    	break; 	
					    case "Match_of_rubber_but_categories":
					    	temp.add(Match_of_rubber_but_categories.get(i));
					    	break; 
					    case "FT_but_categories":
					    	temp.add(FT_but_categories.get(i));
					    	break; 
					    case "FS_oil_categories":
					    	temp.add(FS_oil_categories.get(i));
					    	break; 
					    case "FS_rubber_categories":
					    	temp.add(FS_rubber_categories.get(i));
					    	break; 
					    case "BIAS_T_2_0001":
					    	temp.add(BIAS_T_2_0001.get(i));
					    	break; 
					    case "BIAS_T_2_001":
					    	temp.add(BIAS_T_2_001.get(i));
					    	break; 
					    case "BIAS_T_2_01":
					    	temp.add(BIAS_T_2_01.get(i));
					    	break; 
					    case "BIAS_T_3_0001":
					    	temp.add(BIAS_T_3_0001.get(i));
					    	break; 
					    case "BIAS_T_3_001":
					    	temp.add(BIAS_T_3_001.get(i));
					    	break; 
					    case "BIAS_T_3_01":
					    	temp.add(BIAS_T_3_01.get(i));
					    	break;
					    case "BIAS_T_4_0001":
					    	temp.add(BIAS_T_4_0001.get(i));
					    	break; 
					    case "BIAS_T_4_001":
					    	temp.add(BIAS_T_2_001.get(i));
					    	break; 
					    case "BIAS_T_4_01":
					    	temp.add(BIAS_T_4_01.get(i));
					    	break; 
					    case "BIAS_T_5_0001":
					    	temp.add(BIAS_T_5_0001.get(i));
					    	break; 
					    case "BIAS_T_5_001":
					    	temp.add(BIAS_T_5_001.get(i));
					    	break; 
					    case "BIAS_T_5_01":
					    	temp.add(BIAS_T_5_01.get(i));
					    	break; 
						default:
							break;						
					}
					
				}					
				temp.add(feature_target.get(i));		
			}	
			//temp.add(records.get(i).get(records.get(i).size()-1));	
			result.add(temp);
		}		
		try {
		writeCSV("", output_filename,result);
		} catch (IOException e) {
			System.out.println("[ERROR] I/O Exception.");
			e.printStackTrace();
		}
	}
	
    public static HashMap<Integer, String> featureExtraction_target(ArrayList<ArrayList<String>> records) {
    	HashMap<Integer, String> result = new HashMap<>();
    	int index_of_target_att = records.get(0).size()-1;
    	for (int i = 1; i < records.size(); i++) {
    	    if (i==1) {
    	    	result.put(i, "Rise"); 
    	    	continue;
    	    }
    	    if (Double.parseDouble(records.get(i).get(index_of_target_att))- Double.parseDouble(records.get(i-1).get(index_of_target_att)) >= 0 ) {
    	    	result.put(i, "Rise");     
    	    } else {
    	    	result.put(i, "Down");  
    	    }	
    	}    	  
    	return result;  
    	
    }
           
    public static HashMap<Integer, String> MACD(int tl, int sl, int ll, String att, ArrayList<ArrayList<String>> records) {
    	HashMap<Integer, String> result = new HashMap<>(); 
    	for (int i = 1; i < records.size(); i++) {
    	    double MACD = DIF(i, sl, ll, records) - DEM(i, sl, ll, tl, records);        	
    		if (MACD < 0) {
    			result.put(i, "MACD_" + att.charAt(0) + sl + ll+"_0");
    		} else {
    			result.put(i, "MACD_" + att.charAt(0) + sl + ll+"_1");			
    		}
    	}
    	return result;
    } 
    
    public static HashMap<Integer, Double> MACD_weka(int tl, int sl, int ll, String att, ArrayList<ArrayList<String>> records) {
    	HashMap<Integer, Double> result = new HashMap<>(); 
    	for (int i = 1; i < records.size(); i++) {
    	    double MACD = DIF(i, sl, ll, records) - DEM(i, sl, ll, tl, records);        	
    		result.put(i, MACD);
    	}
    	return result;
    } 
     
    public static double EMA(int t, int l, ArrayList<ArrayList<String>> records, String s) {
    	if (t == 0) {  
    		return 0.0;
    	}
    	int col = 2;
    	double alpha = 2/(double)(l+1);
    	double p = Double.parseDouble(records.get(t).get(col));
        if (s.equals("sl")) {
        	temp_sl.put(0, 0.0);
            temp_sl.put(t, temp_sl.get(t-1) + alpha*(p - temp_sl.get(t-1)));
            return temp_sl.get(t);
        } else {  	
        	temp_ll.put(0, 0.0); 
            temp_ll.put(t, temp_ll.get(t-1) + alpha*(p - temp_ll.get(t-1)));
            return temp_ll.get(t);
        }  
    	
    }
    
    public static double DIF(int t, int sl, int ll, ArrayList<ArrayList<String>> records) {
        return EMA(t, sl, records, "sl") - EMA(t, ll, records, "ll"); 	
    }
    
    public static double DEM(int t, int sl, int ll, int tl, ArrayList<ArrayList<String>> records) {
        return 	(DIF(t, sl, ll, records) + DIF(t-1, sl, ll, records))/(double) tl;
    }
        
	static void writeCSV(String path, String filename, ArrayList<ArrayList<String>> records) throws IOException{
		FileWriter outputFW = new FileWriter(path + filename);
		for(int i=0;i<records.size();i++){
			ArrayList<String> record = records.get(i);
			StringBuilder recordSB = new StringBuilder();
			for(String col : record) recordSB.append(col).append(',');
			recordSB.deleteCharAt(recordSB.length()-1);
			outputFW.write(recordSB.toString());
			if(i < records.size()-1) outputFW.write("\r\n");
		}
		outputFW.close();
	}	
}

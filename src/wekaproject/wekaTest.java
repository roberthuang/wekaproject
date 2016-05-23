package wekaproject;
import java.io.*;
import java.util.*;

import ca.pfv.spmf.algorithms.sequentialpatterns.BIDE_and_prefixspan_with_strings.AlgoPrefixSpan_with_Strings;
import ca.pfv.spmf.input.sequence_database_list_strings.SequenceDatabase;
import dataPreprocessing.SAXTransformation;
import dataPreprocessing.SAXTransformation_Testing;
import getAttribute.GetAttr;
import ruleGeneration.RuleEvaluation;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.NominalPrediction;

import weka.classifiers.functions.LibSVM;
import weka.classifiers.trees.J48;
import weka.core.FastVector;
import weka.core.Instances;
import weka.core.converters.CSVLoader;


import transferToSDB.T2SDB;
import weka.core.converters.ArffSaver;
public class wekaTest {
	
	static HashSet<List<String>> powerSet = new HashSet<List<String>>();
	
	public static BufferedReader readDataFile(String filename) {
		BufferedReader inputReader = null;
 
		try {
			inputReader = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException ex) {
			System.err.println("File not found: " + filename);
		}
 
		return inputReader;
	}
 
	public static Evaluation classify(Classifier model,
			Instances trainingSet, Instances testingSet) throws Exception {
		Evaluation evaluation = new Evaluation(trainingSet);
 
		model.buildClassifier(trainingSet);
		evaluation.evaluateModel(model, testingSet);
 
		return evaluation;
	}
 
	public static double calculateAccuracy(FastVector predictions) {
		double correct = 0;
 
		for (int i = 0; i < predictions.size(); i++) {
			NominalPrediction np = (NominalPrediction) predictions.elementAt(i);
			if (np.predicted() == np.actual()) {
				correct++;
			}
		}
 
		return 100 * correct / predictions.size();
	}
 
	public static void run(int period, List<String> para_list, String preprocessing_path, String output_path) throws Exception {

		BufferedReader datafile = readDataFile(preprocessing_path + "weka_training_" + period + "_" + para_list +".arff");
 
		Instances data = new Instances(datafile);
		//System.out.println(data.numAttributes() - 1);
		data.setClassIndex(data.numAttributes() - 1);
		
		
		int trainSize = (int) Math.round(data.numInstances() * 0.8);
		int testSize = data.numInstances() - trainSize;
		Instances train = new Instances(data, 0, trainSize);
		//System.out.println(train);
		Instances test = new Instances(data, trainSize, testSize);
		
		
		// Use a set of classifiers
		Classifier[] models = { 
				new J48(), // a decision tree			
//				new Logistic(),	
				new LibSVM(),
		};
 
		// Run for each model
		for (int j = 0; j < models.length; j++) {
			    
			//SVM MODULE, SET KERNEL
            if (j == 1) {
            	
            	
            	try {        	
            		 //LINEAR
            	    String options = ( "-K 0" );
            	    String[] optionsArray = options.split( " " );
            	    models[j].setOptions(optionsArray);                        	
    		        Evaluation validation = classify(models[j], train, test);
    		        FastVector predictions = new FastVector();
    		        predictions.appendElements(validation.predictions());
    		        		     
    		        double percentage  = validation.correct()/(double)(validation.incorrect() + validation.correct());
		            if (percentage < 0.78) continue;
    		        
                	File fout = new File(output_path + "svm_liner_"+ period + "_" + para_list +".arff");                	
             	    FileOutputStream fos = new FileOutputStream(fout);
                    OutputStreamWriter osw = new OutputStreamWriter(fos);            	
            	    
            	    
            	
            	   
    		        osw.write(validation.toSummaryString("\nResults:SVM(LINEAR)\n======\n", true)); 
    		        osw.write("\r\n");
    		        osw.write(validation.toClassDetailsString());
    		        //System.out.println(validation.toSummaryString("\nResults:SVM(LINEAR)\n======\n", true));
    		        //System.out.println(validation.toClassDetailsString());
    		        osw.close();
            	}catch (IOException e) {
    	        	System.out.println("[ERROR] I/O Exception.");
    	            e.printStackTrace();  	
    	        }   
    		    
            	try {        	
            		 //POLY
    		        String options = ( "-K 1" );
            	    String[] optionsArray = options.split( " " );    		   
            	    models[j].setOptions(optionsArray);      
            	    Evaluation validation = classify(models[j], train, test);
            	    validation = classify(models[j], train, test);
            	    FastVector predictions = new FastVector();
    		        predictions.appendElements(validation.predictions());
    		        double percentage  = validation.correct()/(double)(validation.incorrect() + validation.correct());
		            if (percentage < 0.78) continue;
            		
                	File fout = new File(output_path + "svm_poly_" + period + "_" + para_list +".arff");                	
             	    FileOutputStream fos = new FileOutputStream(fout);
                    OutputStreamWriter osw = new OutputStreamWriter(fos);   
                    
    		    
    		    
    		        
    		        osw.write(validation.toSummaryString("\nResults:SVM(PLOY)\n======\n", true)); 
    		        osw.write("\r\n");
    		        osw.write(validation.toClassDetailsString());
    		        osw.close();
            	}catch (IOException e) {
    	        	System.out.println("[ERROR] I/O Exception.");
    	            e.printStackTrace();  	
    	        }   
            	
            } else {
                try {        
                	// Collect every group of predictions for current model in a FastVector
			        FastVector predictions = new FastVector();
		            Evaluation validation = classify(models[j], train, test); 
		            predictions.appendElements(validation.predictions());
		            double percentage  = validation.correct()/(double)(validation.incorrect() + validation.correct());
		            if (percentage < 0.78) continue;
		            
                    File fout = new File(output_path + models[j].getClass().getSimpleName() + "_" + period + "_" + para_list +".arff");                	
             	    FileOutputStream fos = new FileOutputStream(fout);
                    OutputStreamWriter osw = new OutputStreamWriter(fos);   			        
 
		    // Uncomment to see the summary for each training-testing pair.
		    //System.out.println(models[j].toString());
			

			// Calculate overall accuracy of current classifier on all splits
			//double accuracy = calculateAccuracy(predictions);
			
			// Print current classifier's name and accuracy in a complicated,
			// but nice-looking way.
			//System.out.println("Accuracy of " + models[j].getClass().getSimpleName() + ": "
			//		+ String.format("%.2f%%", accuracy)
			//		+ "\n---------------------------------");
		            osw.write(validation.toSummaryString("\nResults:"+models[j].getClass().getSimpleName() + "\n======\n", true)); 
    		        osw.write("\r\n");
    		        osw.write(validation.toClassDetailsString());
		            //System.out.println(validation.toSummaryString("\nResults:"+models[j].getClass().getSimpleName() + "\n======\n", true));
		            //System.out.println(validation.toClassDetailsString());
		            osw.close();
                }catch (IOException e) {
    	        	System.out.println("[ERROR] I/O Exception.");
    	            e.printStackTrace();  	
    	        }   		           
		    
            }
		}
	}
	
	private static void buildPowerSet(List<String> list, int count) {
	   
		    powerSet.add(list);
		 
	 	    for(int i=0; i<list.size(); i++)
	 	    {
	 	        List<String> temp = new ArrayList<String>(list);
	 	        temp.remove(i);
	 	        buildPowerSet(temp, temp.size());
	 	    }
	}
	
	static ArrayList<ArrayList<String>> readCSV(String fullpath) throws FileNotFoundException{
        ArrayList<ArrayList<String>> records = new ArrayList<>();
	    File inputFile = new File(fullpath);
	    Scanner scl = new Scanner(inputFile);
	    while(scl.hasNextLine()){
		    ArrayList<String> newRecord = new ArrayList<>();
		    String[] tokens = scl.nextLine().split(",");
		    for(String token : tokens){
			    newRecord.add(token);
		    }
		    records.add(newRecord);
	    }
	    scl.close();		
	    return records; 
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
	
	
	 public static ArrayList<ArrayList<String>> read_text_weka(String filename) throws FileNotFoundException {
	     ArrayList<ArrayList<String>> result = new ArrayList<>();    	
	     Scanner sc = new Scanner(new File(filename));
	     int i = 1;
	     while(sc.hasNextLine()){
		     String[] tokens = sc.nextLine().split(", ");  
			 ArrayList<String> temp = new ArrayList<>();  
			 if (i == 1) {		    
			     for (String s : tokens) {
			         temp.add(s);
			     }   
			     i--;
			     result.add(temp); 
			 } else {
			     for (String s : tokens) {
			         temp.add(s);
			     }   
			     result.add(temp); 
			 }
		  }
		  return result;			
	}
	 
	public static void main(String[] args) throws Exception {		
		/**�ѼƳ]�w**/		
		int N = 5;
		int Original_Level = 1;
		int Original_Relative = 1;
		int Original_Data = 1;
		int MA_Relative = 1;
		int MA_N = 0;
        int MA_Diff = 1;
		int user_defined_class = 0;
        int minsup = 22;
        double minconf = 0.6;
        if (args.length < 4) {
		    System.out.println("Please input: (1) data_path  (2) preprocessing_path  (3) output_path  (4) periods"); 	
		}
        
		String data_path = args[0];
		String preprocessing_path = args[1];
		String output_path = args[2];
		int period = Integer.parseInt(args[3]); 
		
		
		ArrayList<String> parameter = new ArrayList<>();
		parameter.add("B_N_C_" + period);
		parameter.add("B_N_S_" + period);
		parameter.add("B_N_R_" + period);
		parameter.add("B_N_T_" + period);
		if (MA_N == 1) {
		    parameter.add("M_N_C_" + period);
		    parameter.add("M_N_S_" + period);
		    parameter.add("M_N_R_" + period);
		    parameter.add("M_N_T_" + period);
		} 
		if (MA_Diff == 1){		
		    parameter.add("D_N_C_" + period);
		    parameter.add("D_N_S_" + period);
		    parameter.add("D_N_R_" + period);
		    parameter.add("D_N_T_" + period);
		}
		
		if (MA_Relative == 1) {
		    parameter.add("M_R_C_" + period);
		    parameter.add("M_R_S_" + period);
		    parameter.add("M_R_R_" + period);
		    parameter.add("M_R_T_" + period);
		}
		
		//buildPowerSet(parameter, parameter.size());
		
		
		/**Sequential Pattern Mining**/
		//1.1 SAX Training
		SAXTransformation.start("SAXTransformation_config_petro_subset1_2010.txt");
		String path = data_path;    	    
	    ArrayList<ArrayList<String>> records = readCSV(path);
	    HashMap<Integer, String> feature_target = new HashMap<>();
	    /**Feature Extraction**/    	
	    if (user_defined_class == 1) {
	    	feature_target = GetAttr.featureExtraction_target_user_defined(records);
	    } else {
	    	feature_target = GetAttr.featureExtraction_target(records);
	    }    
	    
	    
		String path_after_discrete = "petro_subset1_2010_rate_after_sax_training.csv";
		T2SDB t = new T2SDB();
		int SDB_Training_Size = t.translate_training_sliding_window(N, path_after_discrete,  feature_target, "SDB(Training).txt");
		//1.2 SAX Testing
	    SAXTransformation_Testing.start("petro_subset1_breakpoints_2010.txt");
	
	    SequenceDatabase sequenceDatabase = new SequenceDatabase();
	    sequenceDatabase.loadFile("SDB(Training).txt");
	    
	    AlgoPrefixSpan_with_Strings algo = new AlgoPrefixSpan_with_Strings(); 
	    algo.runAlgorithm(sequenceDatabase, "sequential_patterns.txt", minsup);   
	    //Rule
	    int rule_size = RuleEvaluation.start("RuleEvaluation_config.txt", minconf, minsup, N, SDB_Training_Size);
	    
		int haha = 0;
		if (haha == 1){
		for (List<String> para_list : powerSet) {		
			if (para_list.isEmpty()) continue;
  
    	    GetAttr.featureExtraction_weka(Original_Relative, Original_Data, preprocessing_path + "weka_"  + period + "_" + para_list +".csv" , records, feature_target, period, para_list);  
    	    //System.out.println(para_list);
    	    /**Translate To SDB**/
    	    /**1.Training Data**/
    	    
    	    T2SDB t2sdb = new T2SDB();   
    	    
    	    t2sdb.translate_training_sliding_window_weka_including_level(N, preprocessing_path + "weka_"  + period + "_" + para_list +".csv", feature_target, preprocessing_path+"weka_training_" + period + "_" + para_list +".txt", Original_Level, records, records.get(0).size()-1);
    	    
    	    try {
                ArrayList<ArrayList<String>> txt_training = read_text_weka(preprocessing_path+"weka_training_" + period + "_" + para_list +".txt");  
                try {
    		        writeCSV("", preprocessing_path + "weka_training_" + period + "_" + para_list +".csv", txt_training);
    		    } catch (IOException e) {
   			        System.out.println("[ERROR] I/O Exception.");
    			    e.printStackTrace();
   		        }  
            } catch (FileNotFoundException e) {
               
            }
    	    
    	    
    	    // load CSV
    	    CSVLoader loader = new CSVLoader();
    	    loader.setSource(new File(preprocessing_path + "weka_training_" + period + "_" + para_list+".csv"));
    	    Instances data1 = loader.getDataSet();
    	    // save ARFF
    	    ArffSaver saver = new ArffSaver();
    	    saver.setInstances(data1);
    	    saver.setFile(new File(preprocessing_path + "weka_training_" + period + "_" + para_list +".arff"));
    	    //saver.setDestination(new File(args[1]));
    	    saver.writeBatch();    	    
    	    run(period, para_list, preprocessing_path, output_path);
            
		}
	}
		//Clear
		powerSet = new HashSet<List<String>>();
		
	}
	
	
	
}

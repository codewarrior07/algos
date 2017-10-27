
public class CompareVersions {

	public static void main(String[] args) {
		CompareVersions c = new CompareVersions();
		System.out.println(c.compareVersion1("1","1.0.1"));
	}
	
	public int compareVersion(String version1, String version2) {
	    String[] arr1 = version1.split("\\.");
	    String[] arr2 = version2.split("\\.");
	 
	    int i=0;
	    while(i<arr1.length || i<arr2.length){
	        if(i<arr1.length && i<arr2.length){
	            if(Integer.parseInt(arr1[i]) < Integer.parseInt(arr2[i])){
	                return -1;
	            }else if(Integer.parseInt(arr1[i]) > Integer.parseInt(arr2[i])){
	                return 1;
	            }
	        } else if(i<arr1.length){
	            if(Integer.parseInt(arr1[i]) != 0){
	                return 1;
	            }
	        } else if(i<arr2.length){
	           if(Integer.parseInt(arr2[i]) != 0){
	                return -1;
	            }
	        }
	 
	        i++;
	    }
	 
	    return 0;
	}
	
	public int compareVersion1(String version1, String version2) {
	    String[] levels1 = version1.split("\\.");
	    String[] levels2 = version2.split("\\.");

	    int length = levels1.length>levels2.length ? levels1.length : levels2.length;
	    for (int i=0; i<length; i++) {
	    	int v1=0,v2=0;
	    	if(i < levels1.length)
	    		v1=Integer.parseInt(levels1[i]);
	    	if(i < levels2.length)
	    		v2=Integer.parseInt(levels2[i]);
	    	if(v1>v2)
	    		return 1;
	    	else if(v1<v2)
	    		return -1;
	    }

	    return 0;
	}

}

clean:	
	rm -rf *.class *.aux *.log *.fdb_latexmk *.fls *.synctex.gz *.out 

doc:	
	javadoc -d docprog -author -version -private -linksource *.java
	javadoc -d docuser -author -version *.java
# get a list of sequences, given a gene
# our exmaple will be KRAS, BRAF
#hgnc_symbol


# set to workspace that you like
getwd()
setwd("C:/Users/Anna/Documents/Github/Biology")
getwd()

# start up the library
library(biomaRt)

# get the appropriate datamart
ensembl_mart <- useMart("ensembl", dataset = "hsapiens_gene_ensembl")

# read in a csv file of gene names
genes <- read.csv("test_genes.csv")

# read in the column of gene names
hgnc <- genes$hgnc_symbol

# get the peptide sequences
seq <- getSequence(id= hgnc, type="hgnc_symbol", seqType="peptide", mart = ensembl_mart)

# write to a csv file
write.csv(seq, file = "output_test_gene.csv")
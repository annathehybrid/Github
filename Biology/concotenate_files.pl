#!/usr/bin/perl
use strict;
use warnings;


##########################################################################
##########################################################################
# Version 1.0
#
# This file will take a certain type of file, and squish them together
# note: this has a modified header and content subroutine
# which gets rid of double headers
#
# Input: All files in a folder
# note: user specifies which type of file to concotenate
# Output: Large file with little files squished into one
#
##########################################################################
##########################################################################

# open current directory
opendir(DIR, ".") or die "oh dear something bad has happened ... looks like you can't open the directory\n";
my @names_of_all_files = readdir(DIR); 
closedir(DIR);

# filter out the . and .. files
# get the name of the input files

my @filtered_files = ();
my $summary_excel = 0;


# write in the regex which type of file you want to concatenate
foreach my $file (@names_of_all_files) {
	if ($file =~ m/.txt/ or $file =~ m/.csv/) {
		$summary_excel = $file;
		#print "your summary file is $summary_excel\n";
		push(@filtered_files, $summary_excel);
	};
};

# write name of output file
# and headers if you want
open(OUTPUTFILE, ">output.csv"); #open for write
print OUTPUTFILE "Row,Gene\n";

foreach my $file (@filtered_files) {

	open (FILENAME, "$file");
	my (@master_lines) = <FILENAME>;
	close(FILENAME);
	
	my ($master_first_headers, $master_contents) = &get_headers_and_contents(@master_lines);
	my @master_first_headers = @$master_first_headers;
	my @master_contents = @$master_contents;
	
	foreach my $line (@master_contents) {
		$line =~ s/\r|\n//g;
		print OUTPUTFILE "$line\n";
	};
};


close(OUTPUTFILE);

sub get_headers_and_contents {
# this subroutine will get the headers and contents
# headers will be an array, separated by each header
# contents will be an array, separated by each row
	my (@lines) = @_;

	# get the headers
	# this modification gets rid of the headers
	my $firstline = shift(@lines);
	##my $secondline = shift(@lines);
	my @first_headers = split("\t", $firstline);

	# "chomping" headers
	foreach my $string (@first_headers) {
		$string =~ s/\r|\n//g;
	};

	return (\@first_headers, \@lines);
};
/*
 * The file commons-cli-1.2.jar is under Apache License Version 2.0. 
 * For more details read the file "Apache License.txt" or check it on their website: 
 *      <http://www.apache.org/licenses/LICENSE-2.0.txt> 
 *
 * All other components of this software is under dual licensed under GNU General Public License v2 (GPL-2) 
 * for personal usage for commercial usage you must contact the author prior distribution, usage.
 *
 * @Author: Rodrigo Mansueli Nunes
 * @e-mail: mansueli@ualberta.ca
 * @site: http://kyllo.com.br
 */
package ece422.utils;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 *
 * @author mansueli
 */
public class CliParser {

    private static final Logger log = Logger.getLogger(CliParser.class.getName());
    private File input;
    private File output;
    private double cHazard, jHazard;
    private int time;
    private String[] args = null;
    private final Options options = new Options();

    /**
     *
     * @param args command lines arguments (same from main)
     */
    public CliParser(String[] args) {
        this.args = args;
        options.addOption("h", "help", false, "show help.");
        options.addOption("j", "java-hazard", true, "Defines the Java Hazard value (Default = 0.15).");
        options.addOption("c", "c-hazard", true, "Defines the C Hazard value (Default = 0.11).");
        options.addOption("t", "time", true, "Defines the time (s) that is acceptable\n"
                + " for running the sorting algorithm (Default=6)");
        options.addOption("i", "input", true, "Specifies the output file (Default = input.txt).");
        options.addOption("o", "output", true, "Specifies the output file (Default = output.txt).");
    }

    /**
     * Parsing method, which verify the inputs or terminate the program before
     * continuing in the main program.
     */
    public void parse() {
        CommandLineParser parser = new BasicParser();
        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);

            if (cmd.hasOption("h")) {
                help();
            } else {
                jHazard = cmd.hasOption("j") ? Double.parseDouble(cmd.getOptionValue("j")) : 0.15;
                if (jHazard > 0.5) {
                    throw new ParseException("Invalid jHazard value.\n Hazard values must be between 0.0 and 0.5\n");
                }
                cHazard = cmd.hasOption("c") ? Double.parseDouble(cmd.getOptionValue("c")) : 0.11;
                if (cHazard > 0.5) {
                    throw new ParseException("Invalid cHazard value.\n Hazard values must be between 0.0 and 0.5\n");
                }
                time = cmd.hasOption("t") ? Math.abs(Integer.parseInt(cmd.getOptionValue("t"))) : 6;
                input = new File(cmd.hasOption("i") ? cmd.getOptionValue("i") : "input.txt");
                output = new File(cmd.hasOption("o") ? cmd.getOptionValue("o") : "output.txt");
                if (!input.exists()) {
                    throw new Exception("File not found : " + input.getName());
                }
                if (output.exists()) {
                    throw new Exception("File already exists : " + output.getName());
                } else {
                    output.createNewFile();
                }
            }
        } catch (ParseException e) {
            log.log(Level.SEVERE, "Failed to parse comand line properties", e);
            help();
        } catch (Exception e) {
            log.log(Level.SEVERE, "File error: ", e);
            help();
        }

    }

    private void help() {
        // This prints out some help
        HelpFormatter formater = new HelpFormatter();
        formater.printHelp("Main", options);
        System.exit(0);
    }

    public File getInput() {
        return input;
    }

    public void setInput(File input) {
        this.input = input;
    }

    public File getOutput() {
        return output;
    }

    public void setOutput(File output) {
        this.output = output;
    }

    public double getcHazard() {
        return cHazard;
    }

    public void setcHazard(double cHazard) {
        this.cHazard = cHazard;
    }

    public double getjHazard() {
        return jHazard;
    }

    public void setjHazard(double jHazard) {
        this.jHazard = jHazard;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

}

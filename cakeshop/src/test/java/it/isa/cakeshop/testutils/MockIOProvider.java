package it.isa.cakeshop.testutils;

import it.isa.cakeshop.io.IOProvider;
import java.util.*;

public class MockIOProvider implements IOProvider {
  private Queue<String> inputs = new LinkedList<>();
  private List<String> outputs = new ArrayList<>();

  public void addInput(String input) {
    inputs.add(input);
  }

  public List<String> getOutputs() {
    return outputs;
  }

  @Override
  public String readLine(String prompt) {
    outputs.add(prompt);
    return inputs.poll();
  }

  @Override
  public void print(String message) {
    outputs.add(message);
  }

  @Override
  public void println(String message) {
    outputs.add(message + "\n");
  }
}

public class Lens {
  private String label;
  private Integer focalLength;

  // Constructor
  public Lens(String label, Integer focalLength) {
    this.label = label;
    this.focalLength = focalLength;
  }

  // Getter for label
  public String getLabel() {
    return label;
  }

  // Setter for label
  public void setLabel(String label) {
    this.label = label;
  }

  // Getter for focalLength
  public Integer getFocalLength() {
    return focalLength;
  }

  // Setter for focalLength
  public void setFocalLength(Integer focalLength) {
    this.focalLength = focalLength;
  }
}

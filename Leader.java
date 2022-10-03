import java.util.stream.Stream;

class Leader{
	public String name;
	public Long time;
	public Leader(String name, long time){
		this.name=name;
		this.time = time;
	}
	public String toString() {
        return this.name + ":" + this.time;
    }
}
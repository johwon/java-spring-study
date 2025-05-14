public class MusicPlayer {
    private Speaker speaker;

    public MusicPlayer(Speaker speaker){
        this.speaker = new BluetoothSpeaker();
    }

    public void play(){
        speaker.playSound();
        System.out.println("뮤직 플레이어 실행중..");
    }
}

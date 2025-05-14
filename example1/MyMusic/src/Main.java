//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    public static void main(String[] args) {
        // 객체 직접 생성
        Speaker regularSpeaker = new RegularSpeaker();
        MusicPlayer musicPlayer = new MusicPlayer(regularSpeaker);
        musicPlayer.play();

        // 객체 직접 생성
        Speaker bluetoothSpeaker = new BluetoothSpeaker();
        MusicPlayer musicPlayer2 = new MusicPlayer(bluetoothSpeaker);
        musicPlayer2.play();
    }
}
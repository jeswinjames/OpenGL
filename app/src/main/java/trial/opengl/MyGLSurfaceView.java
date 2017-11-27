package trial.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;



/**
 * Created by jprince on 7/18/2017.
 */

class MyGLSurfaceView extends GLSurfaceView {

    private final AirHockeyRenderer renderer;
    private float previousX = -1;
    private float previousY = -1;

    public MyGLSurfaceView(Context context) {
        super(context);

        setEGLContextClientVersion(2);

        renderer = new AirHockeyRenderer(getContext());

        setRenderer(renderer);

        setRenderMode(RENDERMODE_WHEN_DIRTY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:

                float dx = x - previousX;
                float dy = y - previousY;

                // reverse direction of rotation above the mid-line
                if (y > getHeight() / 2) {
                    dx = dx * -1 ;
                }

                // reverse direction of rotation to left of the mid-line
                if (x < getWidth() / 2) {
                    dy = dy * -1 ;
                }

                renderer.setAngle(
                        renderer.getAngle() -
                                ((dx + dy) * 180 / 320));
                requestRender();
        }

        previousX = x;
        previousY = y;
        return true;
    }
}

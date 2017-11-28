package trial.opengl.data;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import static android.opengl.GLES20.GL_FLOAT;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glVertexAttribPointer;
import static trial.opengl.Constants.BYTES_PER_FLOAT;

/**
 * Created by jprince on 27-Nov-17.
 */

public class VertexData {

    private final FloatBuffer floatBuffer;

    public VertexData(float[] vertexData){
        floatBuffer = ByteBuffer.allocate(vertexData.length * BYTES_PER_FLOAT)
                                .order(ByteOrder.nativeOrder())
                                .asFloatBuffer()
                                .put(vertexData);
    }

    public void setVertexAttribPointer(int dataOffset, int attributeLocation, int componentCount,
                                       int stride){
        floatBuffer.position(dataOffset);
        glVertexAttribPointer(attributeLocation, componentCount, GL_FLOAT,false, stride, floatBuffer);
        glEnableVertexAttribArray(attributeLocation);
        floatBuffer.position(0);
    }
}
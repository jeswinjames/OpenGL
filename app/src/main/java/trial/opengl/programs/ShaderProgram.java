package trial.opengl.programs;

import android.content.Context;
import android.support.annotation.RawRes;

import trial.opengl.util.ShaderHelper;
import trial.opengl.util.TextResourceReader;

import static android.opengl.GLES20.*;

/**
 * Created by jprince on 28-Nov-17.
 */

public class ShaderProgram {
//    uniform constants
    protected static final String U_MATRIX = "u_Matrix";
    protected static final String U_TEXTURE_UNIT = "u_TextureUnit";
//    attribute constants
    protected static final String A_POSITION= "a_Position";
    protected static final String A_COLOR = "a_Color";
    protected static final String A_TEXTURE_COORDINATES = "a_TextureCoordinates";
//    shader program
    protected final int program;

    protected ShaderProgram(Context context,
                            @RawRes int vertexShaderResourceId,
                            @RawRes int fragmentShaderResourceId){
        program = ShaderHelper.buildProgram(
                TextResourceReader.readTextFileFromResource(context, vertexShaderResourceId),
                TextResourceReader.readTextFileFromResource(context, fragmentShaderResourceId));
    }

    public void useProgram(){
        glUseProgram(program);
    }

}

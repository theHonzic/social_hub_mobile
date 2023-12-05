//
//  TextInputWrapper.swift
//  iosApp
//
//  Created by Jan Janovec on 04.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct TextInputWrapper<BackgroundStyle: ShapeStyle, ForegroundStyle: ShapeStyle>: View {
    @Binding var input: String
    var placeholder: String = ""
    var background: BackgroundStyle
    var foreground: ForegroundStyle
    var placeholderColor: Color = .gray
    var icon: Image?
    var valid: Bool = true
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            SingleLineTextField(text: $input, placeholder: placeholder, background: background, foreground: foreground, placeholderColor: placeholderColor, icon: icon)
            if !valid {
                HStack(alignment: .center, spacing: 8) {
                    Image(systemName: "exclamationmark.circle.fill")
                    Text("Not validos")
                }
                .font(.callout)
                .foregroundStyle(Color.red)
            }
        }
    }
}

#Preview {
    TextInputWrapper(input: .constant(""), background: Color.red, foreground: Color.green)
        .padding()
}
